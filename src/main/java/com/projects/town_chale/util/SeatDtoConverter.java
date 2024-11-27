package com.projects.town_chale.util;

import com.projects.town_chale.dto.SeatResponseDto;
import com.projects.town_chale.model.SeatSchedule;

public class SeatDtoConverter {
    public static SeatResponseDto fromSeatSchedule(SeatSchedule seatSchedule) {
        return SeatResponseDto.builder()
                .seatId(seatSchedule.getId())
                .availStatus(seatSchedule.getAvailStatus())
                .build();
    }
}
