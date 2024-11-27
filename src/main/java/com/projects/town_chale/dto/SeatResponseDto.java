package com.projects.town_chale.dto;

import com.projects.town_chale.model.AvailabilityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SeatResponseDto {
    private Long seatId;
    private AvailabilityStatus availStatus;
}
