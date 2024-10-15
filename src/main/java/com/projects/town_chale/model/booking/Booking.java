package com.projects.town_chale.model.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.town_chale.model.BaseModel;
import com.projects.town_chale.model.journey.JourneySeat;
import com.projects.town_chale.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Booking extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("journey_seat")
    @ManyToOne
    @JoinColumn(name = "journey_seat_id")
    private JourneySeat journeySeat;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private BigDecimal amount;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "booking_status")
    @JsonProperty("booking_status")
    private BookingStatus bookingStatus;

}
