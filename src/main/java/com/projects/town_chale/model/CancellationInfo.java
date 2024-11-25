package com.projects.town_chale.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CancellationInfo extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancellation_id", nullable = false)
    private Cancellation cancellation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_info_id", nullable = false)
    private BookingInfo bookingInfo;

    @Column(nullable = false)
    private BigDecimal refundAmount;
}
