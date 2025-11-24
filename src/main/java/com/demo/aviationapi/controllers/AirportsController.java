package com.demo.aviationapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.aviationapi.domain.Airport;
import com.demo.aviationapi.services.AirportService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.constraints.Pattern;

@RestController()
@RequestMapping("/api/v1")
@Validated
public class AirportsController {

    private final AirportService airportService;

    @Autowired
    public AirportsController(final AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping(value = "/airports/{icao}", produces = "application/json")
    @CircuitBreaker(name = "airportByIcao", fallbackMethod = "fallbackAirportByIcao")
    ResponseEntity<?> getAirportByIcao(
        @PathVariable("icao")
        @Pattern(regexp = "^[A-Za-z0-9]{4}$", message = "ICAO must be a 4-character code")
        final String icao)
    {
        return ResponseEntity.ok(getAirportService().getAirportByIcaoCode(icao));
    }

    public ResponseEntity<?> fallbackAirportByIcao(String icao, Throwable t) {
        String message = "Api not available for the moment";
        return ResponseEntity.status(503).body(message);
    }


    private AirportService getAirportService() {
        return airportService;
    }
}
