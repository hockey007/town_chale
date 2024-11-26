package com.projects.town_chale.service;

import com.projects.town_chale.model.Stop;
import com.projects.town_chale.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    public Stop createStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public List<Stop> createStops(List<Stop> stops) {
        return stopRepository.saveAll(stops);
    }

}
