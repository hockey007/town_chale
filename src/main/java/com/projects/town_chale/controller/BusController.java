package com.projects.town_chale.controller;

import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.Bus;
import com.projects.town_chale.service.BusService;
import com.projects.town_chale.validators.BusValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    /**
     * Endpoint to create a new bus with the provided details and dimensions.
     *
     * @param bus  the bus object containing the details to be saved
     * @param rows the number of rows in the bus
     * @param cols the number of columns (seats) in the bus
     * @return a response entity containing the success message and the created bus object
     */
    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<Bus>> createBus(@RequestBody Bus bus,
                                                     @RequestParam Integer rows,
                                                     @RequestParam Integer cols) {
        BusValidator.validateRequest(bus, rows, cols);

        Bus createdBus = busService.createBus(bus);

        SuccessResponse<Bus> response = SuccessResponse.<Bus>builder()
                .message("Bus saved successfully")
                .data(createdBus)
                .build();
        response.setData(createdBus);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
