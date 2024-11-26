package com.projects.town_chale.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScheduleRequestDto {

    private Long busId;
    private Long routeId;

    // TODO: add validations on the schedule time
    private Date departureTime;
    private Date arrivalTime;
    private BigDecimal basePrice;

}
