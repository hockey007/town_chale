package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    @JsonIgnore
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    @JsonIgnore
    private Route route;

    // TODO: update count when seat is added to schedule
    private Integer scheduleSeatCnt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date departureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date arrivalTime;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private Boolean isActive;

}
