package com.projects.town_chale.controller;

import com.projects.town_chale.dto.ScheduleResponseDto;
import com.projects.town_chale.dto.SeatResponseDto;
import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.Schedule;
import com.projects.town_chale.model.ScheduleRequestDto;
import com.projects.town_chale.service.ScheduleService;
import com.projects.town_chale.service.SeatScheduleService;
import com.projects.town_chale.util.ScheduleDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SeatScheduleService seatScheduleService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Schedule>> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule savedSchedule = scheduleService.createSchedule(ScheduleDtoConverter.fromDto(requestDto),
                requestDto.getBusId(),
                requestDto.getRouteId());

        SuccessResponse<Schedule> response = SuccessResponse.<Schedule>builder()
                .message("Bus: " + requestDto.getBusId()
                        + " scheduled successfully for Route: " + requestDto.getRouteId())
                .data(savedSchedule)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<SuccessResponse<List<ScheduleResponseDto>>> getAvailableSchedules(
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
            @RequestParam Long fromStopId,
            @RequestParam Long toStopId
    ) {
        List<ScheduleResponseDto> schedules = scheduleService.getScheduledBusesByDateAndRoute(fromStopId, toStopId, date);
        SuccessResponse<List<ScheduleResponseDto>> response = SuccessResponse.<List<ScheduleResponseDto>>builder()
                .message("Fetched Buses successfully")
                .data(schedules)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/seats/available")
    public ResponseEntity<SuccessResponse<List<SeatResponseDto>>> getAvailableSeats(
            @RequestParam Long scheduleId,
            @RequestParam Long fromStopId,
            @RequestParam Long toStopId
    ) {
        List<SeatResponseDto> availableSeats = seatScheduleService.getAvailableSeats(scheduleId, fromStopId, toStopId);
        SuccessResponse<List<SeatResponseDto>> response = SuccessResponse.<List<SeatResponseDto>>builder()
                .message("Fetched available seats successfully")
                .data(availableSeats)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
