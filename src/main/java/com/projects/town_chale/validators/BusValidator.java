package com.projects.town_chale.validators;

import com.projects.town_chale.exception.InvalidBusDetailsException;
import com.projects.town_chale.model.Bus;

import java.util.Date;

public class BusValidator {

    /**
     * Validates the bus details and the seat dimensions.
     *
     * @param bus  the bus object to be validated
     * @param rows the number of rows in the bus
     * @param cols the number of columns (seats) in the bus
     */
    public static void validateRequest(Bus bus, Integer rows, Integer cols) {
        if (rows == null || cols == null || rows <= 0 || cols <= 0) {
            throw new InvalidBusDetailsException("Invalid dimensions provided: rows and columns must be greater than zero.");
        }

        if (bus == null) {
            throw new InvalidBusDetailsException("Bus details cannot be null.");
        }

        if (bus.getRegNumber() == null || bus.getRegNumber().isEmpty()) {
            throw new InvalidBusDetailsException("Bus registration number is required.");
        }

        if (bus.getBusType() == null) {
            throw new InvalidBusDetailsException("Bus type is required.");
        }

        if (bus.getCurrentMileage() == null || bus.getCurrentMileage() < 0) {
            throw new InvalidBusDetailsException("Bus current mileage is required and must be non-negative.");
        }

        if (bus.getInsuranceValidity() == null) {
            throw new InvalidBusDetailsException("Insurance validity is required.");
        }

        if (bus.getInsuranceValidity().before(new Date())) {
            throw new InvalidBusDetailsException("Insurance validity has expired for the bus.");
        }
    }

}
