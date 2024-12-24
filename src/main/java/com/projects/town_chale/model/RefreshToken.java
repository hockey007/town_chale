package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class RefreshToken extends BaseModel<UUID> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private int usageCount;

    private Instant lastUsed;

    @Column(nullable = false)
    private Instant expiresAt;

    private Boolean isValid;

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void incrementUsage() {
        this.usageCount++;
        this.lastUsed = Instant.now();
    }

}
