package com.projects.town_chale.security;

import com.projects.town_chale.exception.RateLimitExceededException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DeviceAndIpMonitoringFilter implements Filter {

    private final Map<String, Integer> ipRequestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> ipBlockTimestamps = new ConcurrentHashMap<>();

    private final Map<String, Integer> fingerprintRequestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> fingerprintBlockTimestamps = new ConcurrentHashMap<>();

    private final int MAX_IP_REQUESTS = 50;
    private final int MAX_FINGERPRINT_REQUESTS = 50;
    private final long RESET_INTERVAL = 60 * 1000;
    private final long COOL_DOWN_PERIOD = 5 * 60 * 1000;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public DeviceAndIpMonitoringFilter() {
        scheduler.scheduleAtFixedRate(() -> {
            ipRequestCounts.clear();
            fingerprintRequestCounts.clear();
        }, RESET_INTERVAL, RESET_INTERVAL, TimeUnit.MILLISECONDS);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            String clientIp = httpRequest.getRemoteAddr();
            String fingerprint = httpRequest.getHeader("X-Device-Fingerprint");
            if (fingerprint == null || fingerprint.isEmpty()) {
                fingerprint = "UNKNOWN";
            }

            if (isInCoolDown(ipBlockTimestamps, clientIp)) {
                sendCoolDownResponse(httpResponse, "IP");
                return;
            }

            if (isInCoolDown(fingerprintBlockTimestamps, fingerprint)) {
                sendCoolDownResponse(httpResponse, "Device");
                return;
            }

            ipRequestCounts.put(clientIp, ipRequestCounts.getOrDefault(clientIp, 0) + 1);
            if (ipRequestCounts.get(clientIp) > MAX_IP_REQUESTS) {
                ipBlockTimestamps.put(clientIp, System.currentTimeMillis());
                throw new RateLimitExceededException("Rate limit exceeded for IP");
            }

            fingerprintRequestCounts.put(fingerprint, fingerprintRequestCounts.getOrDefault(fingerprint, 0) + 1);
            if (fingerprintRequestCounts.get(fingerprint) > MAX_FINGERPRINT_REQUESTS) {
                fingerprintBlockTimestamps.put(fingerprint, System.currentTimeMillis());
                throw new RateLimitExceededException("Rate limit exceeded for device");
            }

            chain.doFilter(request, response);
        } catch (RateLimitExceededException exception) {
            handleException(httpResponse, exception);
        }
    }

    private boolean isInCoolDown(Map<String, Long> blockTimestamps, String key) {
        Long blockTime = blockTimestamps.get(key);
        if (blockTime == null) {
            return false;
        }

        return System.currentTimeMillis() - blockTime < COOL_DOWN_PERIOD;
    }

    private void sendCoolDownResponse(HttpServletResponse response, String type) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        String jsonResponse = String.format(
                "{\"error\": true, \"message\": \"Too many requests\", \"details\": \"%s is in cool-down period\"}", type
        );
        response.getWriter().write(jsonResponse);
    }

    private void handleException(HttpServletResponse response, RateLimitExceededException exception) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

        String jsonResponse = String.format(
                "{\"error\": true, \"message\": \"%s\", \"details\": \"Too many requests\"}", exception.getMessage()
        );
        response.getWriter().write(jsonResponse);
    }
}