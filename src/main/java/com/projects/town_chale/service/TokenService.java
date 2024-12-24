package com.projects.town_chale.service;

import com.projects.town_chale.model.RefreshToken;
import com.projects.town_chale.model.User;
import com.projects.town_chale.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiration}")
    private Long accessTokenExpirationInSeconds;

    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpirationInMillis;

    @Value("${jwt.refresh-token-usage-threshold}")
    private Long refreshTokenUsageThreshold;

    @Value("${jwt.refresh-token-time-threshold}")
    private Long refreshTokenTimeThresholdInDays;

    private final Key signingKey;

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public TokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationInSeconds * 1000))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAccessToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid or expired access token");
        }
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(Instant.now().plusMillis(refreshTokenExpirationInMillis));
        refreshToken.setIsValid(true);
        refreshToken.setUsageCount(0);
        refreshToken.setLastUsed(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public Map<String, String> refreshTokens(String oldRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(oldRefreshToken).orElseThrow(
                () -> new RuntimeException("Invalid refresh token")
        );

        verifyExpiration(refreshToken);
        verifyValidity(refreshToken);

        String newAccessToken = generateAccessToken(refreshToken.getUser());

        if (shouldRotateToken(refreshToken)) {
            revokeToken(refreshToken);
            RefreshToken newRefreshToken = createRefreshToken(refreshToken.getUser());
            return Map.of(
                    "accessToken", newAccessToken,
                    "refreshToken", newRefreshToken.getToken()
            );
        }

        incrementUsage(refreshToken);
        return Map.of(
                "accessToken", newAccessToken,
                "refreshToken", refreshToken.getToken()
        );
    }

    private boolean shouldRotateToken(RefreshToken refreshToken) {
        return refreshToken.getUsageCount() >= refreshTokenUsageThreshold ||
                refreshToken.getLastUsed().isBefore(Instant.now().minus(refreshTokenTimeThresholdInDays, ChronoUnit.DAYS));
    }

    public Map<String, String> generateTokens(User user) {
        String newAccessToken = generateAccessToken(user);
        RefreshToken newRefreshToken = createRefreshToken(user);

        return Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken.getToken()
        );
    }

    private void incrementUsage(RefreshToken refreshToken) {
        refreshToken.incrementUsage();
        refreshToken.setLastUsed(Instant.now());
        refreshTokenRepository.save(refreshToken);
    }

    private void revokeToken(RefreshToken refreshToken) {
        refreshToken.setIsValid(false);
        refreshTokenRepository.save(refreshToken);
    }

    private void verifyValidity(RefreshToken refreshToken) {
        if (!refreshToken.getIsValid()) {
            throw new RuntimeException("Invalid refresh token. Please log in again.");
        }
    }

    private void verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.isExpired()) {
            throw new RuntimeException("Expired refresh token. Please log in again.");
        }
    }
}