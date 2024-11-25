package com.projects.town_chale.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Bus extends BaseModel {

    @Column(unique = true, nullable = false)
    private String regNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusType busType;

    @Column(nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compartment> compartments;

    @Temporal(TemporalType.DATE)
    private Date lastServiceDate;

    @Column(nullable = false)
    private Double currentMileage;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date insuranceValidity;

    @Column(nullable = false)
    private Boolean isActive;

}
