package com.projects.town_chale.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Payment extends BaseModel<Long> {
    private Booking booking;

    // TODO: Implement public facing ids to avoid predictability
    private String transactionId;
    private String paymentGateway;
    private PaymentMode paymentMode;
    private PaymentType paymentType;
    private BigDecimal totalAmount;
    private PaymentStatus paymentStatus;
}
