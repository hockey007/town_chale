package com.projects.town_chale.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.projects.town_chale.model.Route;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RouteRequestDto {

    private Route route;
    private Long srcStopId;
    private Long destStopId;

}
