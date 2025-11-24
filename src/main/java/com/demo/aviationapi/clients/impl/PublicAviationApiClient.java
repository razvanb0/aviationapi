package com.demo.aviationapi.clients.impl;

import java.io.IOException;
import java.security.ProviderException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.demo.aviationapi.clients.AviationApiClient;
import com.demo.aviationapi.dtos.AirportDto;
import com.demo.aviationapi.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PublicAviationApiClient implements AviationApiClient {

    private static final Logger logger = Logger.getLogger(PublicAviationApiClient.class.getName());

    private final RestTemplate restTemplate;

    @Value("${aviation.api.public.base-url}")
    private String baseUrl;

    @Value("${aviation.api.public.version}")
    private String version;

    @Autowired
    public PublicAviationApiClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public AirportDto fetchAirport(String icao) {
        String url = baseUrl + "/" + version + "/airports/?apt=" + icao;

        logger.info(String.format("Calling Public Aviation API for Airport data, ICAO: %s", icao));
        try {
            ResponseEntity<Map<String, List<AirportDto>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders()),
                new ParameterizedTypeReference<>() {}
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<AirportDto> airports = response.getBody().get(icao);
                if (CollectionUtils.isEmpty(airports)){
                    throw new NotFoundException("Airport not found: " + icao);
                }
                logger.info(String.format("Airport with icao=%s retrieved succesfully!", icao));
                return airports.getFirst();
            } else {
                logger.info(String.format(String.format("Airport not found for given icao:%s", icao)));
                throw new NotFoundException("Airport not found: " + icao);
            }
        } catch (HttpServerErrorException e) {
            logger.warning(String.format("API error while fetching ICAO %s: %s", icao, e.getMessage()));
            throw new ProviderException("Error calling Airport API", e);
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
