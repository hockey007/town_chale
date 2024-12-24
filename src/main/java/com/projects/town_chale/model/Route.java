package com.projects.town_chale.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Route extends BaseModel<Long> {
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String routeNumber;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RouteStop> routeStops;

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
