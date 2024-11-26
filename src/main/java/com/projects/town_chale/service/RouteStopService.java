package com.projects.town_chale.service;

import com.projects.town_chale.dto.RouteStopDto;
import com.projects.town_chale.model.Route;
import com.projects.town_chale.model.RouteStop;
import com.projects.town_chale.model.Stop;
import com.projects.town_chale.repository.RouteRepository;
import com.projects.town_chale.repository.RouteStopRepository;
import com.projects.town_chale.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteStopService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private RouteStopRepository routeStopRepository;

    public List<RouteStop> createRouteStops(Long routeId, List<RouteStopDto> routeStopsDto) {
        Route route = routeRepository.findById(routeId).orElseThrow();

        List<RouteStop> routeStops = new ArrayList<>();
        for(RouteStopDto routeStopDto: routeStopsDto) {
            Stop stop = stopRepository.findById(routeStopDto.getStopId()).orElseThrow();

            RouteStop routeStop = RouteStop.builder()
                    .route(route)
                    .stop(stop)
                    .stopOrder(routeStopDto.getStopOrder())
                    .distFromPrevKm(routeStopDto.getDistFromPrevKm())
                    .priceFromPrev(routeStopDto.getPriceFromPrev())
                    .build();

            routeStops.add(routeStop);
        }

        return routeStopRepository.saveAll(routeStops);
    }

}
