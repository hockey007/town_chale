package com.projects.town_chale.controller;

import com.projects.town_chale.dto.RouteRequestDto;
import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.Route;
import com.projects.town_chale.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Route>> createRoute(@RequestBody RouteRequestDto requestDto) {
        Route savedRoute = routeService.createRoute(requestDto.getRoute(),
                requestDto.getSrcStopId(), requestDto.getDestStopId());

        SuccessResponse<Route> response = SuccessResponse.<Route>builder()
                .message("Route saved successfully")
                .data(savedRoute)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
