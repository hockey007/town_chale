package com.projects.town_chale.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Bus extends BaseModel<Long> {

    @Column(unique = true, nullable = false)
    private String regNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusType busType;

    @Column(nullable = false)
    private Integer rows;

    @Column(nullable = false)
    private Integer cols;

    private Integer capacity;

    private Integer noOfCompartments;

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
