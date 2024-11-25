package com.projects.town_chale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class RouteStop extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop stop;

    @Column(nullable = false)
    private Integer stopOrder;

    @Column(nullable = false)
    private Double distFromPrevKm;

    @Column(nullable = false)
    private Double priceFromPrevKm;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
}
