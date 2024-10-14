package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class JourneyCity extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;

    @Column(name = "stop_order")
    @JsonProperty("stop_order")
    private Integer stopOrder;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "arrival_time")
    @JsonProperty("arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    @JsonProperty("departure_time")
    private LocalTime departureTime;

    @Column(name = "distance_from_prev")
    @JsonProperty("distance_from_prev")
    private Double distFromPrevStop;

}
