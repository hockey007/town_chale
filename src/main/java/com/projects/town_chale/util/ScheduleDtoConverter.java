package com.projects.town_chale.util;

import com.projects.town_chale.model.Schedule;
import com.projects.town_chale.model.ScheduleRequestDto;

public class ScheduleDtoConverter {
    public static Schedule fromDto(ScheduleRequestDto requestDto) {
        return Schedule.builder()
                .departureTime(requestDto.getDepartureTime())
                .arrivalTime(requestDto.getArrivalTime())
                .basePrice(requestDto.getBasePrice())
                .isActive(true)
                .build();
    }
}
