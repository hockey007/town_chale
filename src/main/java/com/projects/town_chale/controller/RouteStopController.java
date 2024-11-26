package com.projects.town_chale.controller;

import com.projects.town_chale.dto.RouteStopDto;
import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.RouteStop;
import com.projects.town_chale.service.RouteStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route/stop")
public class RouteStopController {

    @Autowired
    private RouteStopService routeStopService;

    @PostMapping("/create/bulk")
    public ResponseEntity<SuccessResponse<List<RouteStop>>> createRouteStops(@RequestBody List<RouteStopDto> routeStops,
                                                                             @RequestParam Long routeId) {
        List<RouteStop> savedRouteStops = routeStopService.createRouteStops(routeId, routeStops);
        SuccessResponse<List<RouteStop>> response = SuccessResponse.<List<RouteStop>>builder()
                .message("Stops saved successfully for the route")
                .data(savedRouteStops)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
