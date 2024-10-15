package com.projects.town_chale.model.journey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.town_chale.model.BaseModel;
import com.projects.town_chale.model.bus.Bus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JourneySeat extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Column(name = "reservation_type")
    @JsonProperty("reservation_type")
    private ReservationType reservationType;

    @Column(name = "seat_status")
    @JsonProperty("seat_status")
    private SeatStatus seatStatus;

}
