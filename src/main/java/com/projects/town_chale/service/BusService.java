package com.projects.town_chale.service;

import com.projects.town_chale.model.Bus;
import com.projects.town_chale.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    /**
     * Creates a new bus and saves it to the repository.
     *
     * @param bus the bus object to be saved
     * @return the saved bus object
     */
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

}
