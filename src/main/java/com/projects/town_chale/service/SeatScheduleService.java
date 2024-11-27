package com.projects.town_chale.service;

import com.projects.town_chale.dto.SeatResponseDto;
import com.projects.town_chale.dto.SeatScheduleRequestDto;
import com.projects.town_chale.model.Schedule;
import com.projects.town_chale.model.Seat;
import com.projects.town_chale.model.SeatSchedule;
import com.projects.town_chale.model.Gender;
import com.projects.town_chale.model.AvailabilityStatus;
import com.projects.town_chale.repository.ScheduleRepository;
import com.projects.town_chale.repository.SeatRepository;
import com.projects.town_chale.repository.SeatScheduleRepository;
import com.projects.town_chale.repository.SeatScheduleSegmentRepository;
import com.projects.town_chale.util.SeatDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatScheduleService {

    @Autowired
    private SeatScheduleRepository seatScheduleRepository;

    @Autowired
    private SeatScheduleSegmentRepository seatSegmentRepository;

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

    public List<SeatResponseDto> getAvailableSeats(Long scheduleId, Long srcStopId, Long destStopId) {
        List<SeatSchedule> seatSchedules = seatSegmentRepository.findNonConflictingSeats(scheduleId, srcStopId, destStopId);
        System.out.println(seatSchedules);

        if(seatSchedules == null) /* or optional resp from repository */ {
            throw new IllegalArgumentException("No Seats available");
        }

        return seatSchedules.stream()
                .map(SeatDtoConverter::fromSeatSchedule)
                .collect(Collectors.toList());
    }

}
