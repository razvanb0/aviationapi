package com.demo.aviationapi.clients;

import com.demo.aviationapi.dtos.AirportDto;

public interface AviationApiClient {

    /**
     * Fetch airport details by ICAO code
     *
     * @param icao ICAO airport identifier
     * @return the airport details as {@link AirportDto}
     */
    AirportDto fetchAirport(String icao);
}
