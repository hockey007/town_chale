package com.projects.town_chale.util;

import com.projects.town_chale.dto.ScheduleResponseDto;
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

    public static ScheduleResponseDto fromSchedule(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .busId(schedule.getBus().getId())
                .routeId(schedule.getRoute().getId())
                .departureTime(schedule.getDepartureTime())
                .arrivalTime(schedule.getArrivalTime())
                .build();
    }

}
