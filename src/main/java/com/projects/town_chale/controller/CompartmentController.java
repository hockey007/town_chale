package com.projects.town_chale.controller;

import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.Compartment;
import com.projects.town_chale.service.CompartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compartment")
public class CompartmentController {

    @Autowired
    private CompartmentService compartmentService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Compartment>> createCompartment(@RequestBody Compartment compartment,
                                                                          @RequestParam Long busId) {
        Compartment savedCompartment = compartmentService.createCompartment(busId, compartment);

        SuccessResponse<Compartment> response = SuccessResponse.<Compartment>builder()
                .message("Created compartment successfully")
                .data(savedCompartment)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
