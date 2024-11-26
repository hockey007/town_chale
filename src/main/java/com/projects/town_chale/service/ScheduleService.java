package com.projects.town_chale.service;

import com.projects.town_chale.model.Bus;
import com.projects.town_chale.model.Route;
import com.projects.town_chale.model.Schedule;
import com.projects.town_chale.repository.BusRepository;
import com.projects.town_chale.repository.RouteRepository;
import com.projects.town_chale.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    public Schedule createSchedule(Schedule schedule, Long busId, Long routeId) {
        Bus bus = busRepository.findById(busId).orElseThrow();
        Route route = routeRepository.findById(routeId).orElseThrow();

        schedule.setBus(bus);
        schedule.setRoute(route);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduledBusesByDateAndRoute(Long srcStopId, Long destStopId, Date date) {
        List<Route> routes = routeRepository.findByRouteStops(srcStopId, destStopId)
                .orElseThrow(() -> new IllegalArgumentException("No route found for the given stops"));

        List<Schedule> allSchedules = new ArrayList<>();

        for (Route route : routes) {
            List<Schedule> schedules = scheduleRepository.findAllByRoute(route)
                    .orElseThrow(() -> new IllegalArgumentException("No schedules found for route: " + route.getName()));
            allSchedules.addAll(schedules);
        }

        return allSchedules;
    }

}
