package com.projects.town_chale.model.journey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.town_chale.model.BaseModel;
import com.projects.town_chale.model.bus.Bus;
import com.projects.town_chale.model.bus.Driver;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Journey extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private LocalTime departure;

    private LocalTime arrival;

    @Column(name = "days_of_week")
    @JsonProperty("days_of_week")
    private List<Integer> daysOfWeek;

}
