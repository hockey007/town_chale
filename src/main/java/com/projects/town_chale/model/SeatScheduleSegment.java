package com.projects.town_chale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class SeatScheduleSegment extends BaseModel<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_schedule_id", nullable = false)
    private SeatSchedule seatSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_stop_id", nullable = false)
    private RouteStop fromStop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_stop_id", nullable = false)
    private RouteStop toStop;
}
