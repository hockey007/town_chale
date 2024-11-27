package com.projects.town_chale.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.projects.town_chale.model.Bus;
import com.projects.town_chale.model.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScheduleResponseDto {
    private Long busId;
    private Long routeId;
    private Date departureTime;
    private Date arrivalTime;
    private Integer availableSeats;
}
