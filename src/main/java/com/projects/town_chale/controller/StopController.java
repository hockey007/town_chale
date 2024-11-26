package com.projects.town_chale.controller;

import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.model.Stop;
import com.projects.town_chale.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stop")
public class StopController {

    @Autowired
    private StopService stopService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Stop>> createStop(@RequestBody Stop stop) {
        Stop savedStop = stopService.createStop(stop);

        SuccessResponse<Stop> response = SuccessResponse.<Stop>builder()
                .message("Stop saved successfully")
                .data(savedStop)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create/bulk")
    public ResponseEntity<SuccessResponse<List<Stop>>> createStops(@RequestBody List<Stop> stops) {
        List<Stop> savedStops = stopService.createStops(stops);

        SuccessResponse<List<Stop>> response = SuccessResponse.<List<Stop>>builder()
                .message("Stop saved successfully")
                .data(savedStops)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
