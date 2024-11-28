package com.projects.town_chale.service;

import com.projects.town_chale.exception.PassengerDataException;
import com.projects.town_chale.model.Passenger;
import com.projects.town_chale.model.User;
import com.projects.town_chale.repository.PassengerRepository;
import com.projects.town_chale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserRepository userRepository;

    public Passenger addPassenger(Long userId, Passenger passenger) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PassengerDataException("User not found with id: " + userId));

        passenger.setUser(user);
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerDataException("Passenger not found"));

        passengerRepository.deleteById(id);
    }

    public Passenger updatePassenger(Long id, Passenger passenger) {
        Passenger existingPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerDataException("Passenger not found"));

        if (passenger.getName() != null) {
            existingPassenger.setName(passenger.getName());
        }
        if (passenger.getAge() != null) {
            existingPassenger.setAge(passenger.getAge());
        }
        if (passenger.getGender() != null) {
            existingPassenger.setGender(passenger.getGender());
        }

        return passengerRepository.save(existingPassenger);
    }

}
