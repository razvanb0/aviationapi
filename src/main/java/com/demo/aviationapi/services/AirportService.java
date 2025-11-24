package com.demo.aviationapi.services;

import com.demo.aviationapi.domain.Airport;

public interface AirportService {

    Airport getAirportByIcaoCode(final String icao);
}
