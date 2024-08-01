package com.rungroup.backend.controller;

import com.rungroup.backend.exception.UserNotFoundException;
import com.rungroup.backend.model.Flight;
import com.rungroup.backend.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/flights")
    List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @GetMapping("/flight/{id}")
    Flight getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
