package com.projects.town_chale.controller;

import com.projects.town_chale.dto.SeatScheduleRequestDto;
import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.SeatSchedule;
import com.projects.town_chale.service.SeatScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule/seats")
public class SeatScheduleController {

    @Autowired
    private SeatScheduleService seatScheduleService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<List<SeatSchedule>>> createSeatSchedules(
            @RequestBody List<SeatScheduleRequestDto> seatScheduleRequestDtos,
            @RequestParam Long scheduleId
    ) {
        List<SeatSchedule> savedSeatSchedules = seatScheduleService
                .createSeatSchedule(scheduleId, seatScheduleRequestDtos);

        SuccessResponse<List<SeatSchedule>> response = SuccessResponse.<List<SeatSchedule>>builder()
                .message("Successfully created seats for Schedule: " + scheduleId)
                .data(savedSeatSchedules)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
