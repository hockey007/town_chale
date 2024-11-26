package com.projects.town_chale.service;

import com.projects.town_chale.model.Route;
import com.projects.town_chale.model.Stop;
import com.projects.town_chale.repository.RouteRepository;
import com.projects.town_chale.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    public Route createRoute(Route route, Long srcStopId, Long destStopId) {
        Stop srcStop = stopRepository.findById(srcStopId).orElseThrow();
        Stop destStop = stopRepository.findById(destStopId).orElseThrow();

        route.setSrcStop(srcStop);
        route.setDestStop(destStop);
        return routeRepository.save(route);
    }

}
