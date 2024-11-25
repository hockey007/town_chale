package com.projects.town_chale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Route extends BaseModel {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String routeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "src_stop_id", nullable = false)
    private Stop srcStop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dest_stop_id", nullable = false)
    private Stop destStop;

    @Column(nullable = false)
    private Integer distanceInKm;

    @Column(nullable = false)
    private Integer distanceInMinutes;

    @Column(nullable = false)
    private Boolean isActive;
}
