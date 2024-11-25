package com.projects.town_chale.service;

import com.projects.town_chale.exception.BusNotFoundException;
import com.projects.town_chale.model.Bus;
import com.projects.town_chale.model.Compartment;
import com.projects.town_chale.model.Seat;
import com.projects.town_chale.repository.BusRepository;
import com.projects.town_chale.repository.CompartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompartmentService {

    @Autowired
    private CompartmentRepository compartmentRepository;

    @Autowired
    private BusRepository busRepository;

    public Compartment createCompartment(Long busId, Compartment compartment) {
        Bus bus = busRepository.findById(busId).orElseThrow(() ->
                new BusNotFoundException("Bus not found with id: " + busId));

        compartment.setBus(bus);
        for(Seat seat: compartment.getSeats()) {
            seat.setBus(bus);
            seat.setCompartment(compartment);
        }

        List<Compartment> compartments = bus.getCompartments();
        compartments.add(compartment);
        bus.setCompartments(compartments);
        return compartmentRepository.save(compartment);
    }

}
