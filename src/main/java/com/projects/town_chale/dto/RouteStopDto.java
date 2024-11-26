package com.projects.town_chale.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RouteStopDto {

    private Long stopId;
    private Integer stopOrder;
    private Double distFromPrevKm;
    private Double priceFromPrev;

}
