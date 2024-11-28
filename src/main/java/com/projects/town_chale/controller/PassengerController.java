package com.projects.town_chale.controller;

import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.Passenger;
import com.projects.town_chale.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<Passenger>> addPassenger(
            @RequestParam Long userId,
            @RequestBody Passenger passenger
    ) {
        Passenger savedPassenger = passengerService.addPassenger(userId, passenger);

        SuccessResponse<Passenger> response = SuccessResponse.<Passenger>builder()
                .message("Passenger saved successfully")
                .data(savedPassenger)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        SuccessResponse response = SuccessResponse.builder()
                .message("Passenger deleted successfully")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessResponse<Passenger>> updatePassenger(
            @PathVariable Long id,
            @RequestBody Passenger passenger
    ) {
        Passenger savedPassenger = passengerService.updatePassenger(id, passenger);
        SuccessResponse<Passenger> response = SuccessResponse.<Passenger>builder()
                .message("Passenger updated successfully")
                .data(savedPassenger)
                .build();

        return ResponseEntity.ok(response);
    }

}
