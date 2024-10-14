package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "compartment_id")
    private Compartment compartment;

    @Column(name = "seat_num")
    @JsonProperty("seat_num")
    private String seatNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "seat_type")
    @JsonProperty("seat_type")
    private SeatType seatType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "res_type")
    @JsonProperty("res_type")
    private ReservationType reservationType;

}
