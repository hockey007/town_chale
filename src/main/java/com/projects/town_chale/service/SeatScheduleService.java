package com.projects.town_chale.service;

import com.projects.town_chale.dto.SeatScheduleRequestDto;
import com.projects.town_chale.model.*;
import com.projects.town_chale.repository.ScheduleRepository;
import com.projects.town_chale.repository.SeatRepository;
import com.projects.town_chale.repository.SeatScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatScheduleService {

    @Autowired
    private SeatScheduleRepository seatScheduleRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<SeatSchedule> createSeatSchedule(Long scheduleId, List<SeatScheduleRequestDto> seatScheduleRequests) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();

        List<SeatSchedule> seatSchedules = seatScheduleRequests.stream()
                .map(request -> {
                    Seat seat = seatRepository.findById(request.getSeatId()).orElseThrow();
                    return SeatSchedule.builder()
                            .schedule(schedule)
                            .seat(seat)
                            .gender(Optional.ofNullable(request.getGender())
                                    .orElse(Gender.ANY))
                            .availStatus(Optional.ofNullable(request.getAvailabilityStatus())
                                    .orElse(AvailabilityStatus.AVAILABLE))
                            .build();
                }).collect(Collectors.toList());

        return seatScheduleRepository.saveAll(seatSchedules);
    }

}
