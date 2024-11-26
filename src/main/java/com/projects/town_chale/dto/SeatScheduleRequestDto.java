package com.projects.town_chale.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.projects.town_chale.model.AvailabilityStatus;
import com.projects.town_chale.model.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SeatScheduleRequestDto {

    private Long seatId;
    private Gender gender;
    private AvailabilityStatus availabilityStatus;

}
