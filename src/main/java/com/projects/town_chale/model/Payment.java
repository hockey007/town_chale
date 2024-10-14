package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private BigDecimal amount;

    @Column(name = "payment_gateway")
    @JsonProperty("payment_gateway")
    private String paymentGateway;

    @Enumerated
    @Column(name = "payment_status")
    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

}
