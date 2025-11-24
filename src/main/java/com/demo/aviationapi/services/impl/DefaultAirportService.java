package com.demo.aviationapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.aviationapi.clients.AviationApiClient;
import com.demo.aviationapi.domain.Airport;
import com.demo.aviationapi.mappers.AirportMapper;
import com.demo.aviationapi.services.AirportService;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class DefaultAirportService implements AirportService {

    @Autowired
    private AviationApiClient aviationApiClient;

    @Autowired
    private AirportMapper airportMapper;

    @Override
//    @Cacheable(value = "airports", key = "#icao")
    @Retry(name = "airportByIcaoRetry")
    public Airport getAirportByIcaoCode(String icao) {
        return airportMapper.map(getAviationApiClient().fetchAirport(icao));
    }

    protected AviationApiClient getAviationApiClient() {
        return aviationApiClient;
    }
}
