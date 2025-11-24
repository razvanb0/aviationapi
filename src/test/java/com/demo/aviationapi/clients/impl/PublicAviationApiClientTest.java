package com.demo.aviationapi.clients.impl;

import com.demo.aviationapi.dtos.AirportDto;
import com.demo.aviationapi.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
class PublicAviationApiClientTest {

    @Autowired
    private PublicAviationApiClient client;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private static final String BASE_URL = "https://api.aviationapi.com";
    private static final String VERSION = "v2";

    @BeforeEach
    void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void fetchAirport_successfulResponse() {
        // given
        String icao = "KAVL";
        String url = BASE_URL + "/" + VERSION + "/airports/?apt=" + icao;

        String mockResponse = """
            {
                "KAVL": [
                    {
                        "site_number": "16517.5*A",
                        "type": "AIRPORT",
                        "facility_name": "ASHEVILLE RGNL",
                        "faa_ident": "AVL",
                        "icao_ident": "KAVL"
                    }
                ]
            }
        """;

        mockServer.expect(once(), requestTo(url))
            .andExpect(method(org.springframework.http.HttpMethod.GET))
            .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        // when
        AirportDto result = client.fetchAirport(icao);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getIcao_ident()).isEqualTo("KAVL");

        mockServer.verify();
    }

    @Test
    void fetchAirport_emptyList_throwsNotFoundException() {
        // given
        String icao = "KXYZ";
        String url = BASE_URL + "/" + VERSION + "/airports/?apt=" + icao;

        String mockResponse = """
            {
                "KXYZ": []
            }
        """;

        mockServer.expect(once(), requestTo(url))
            .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        // when
        assertThrows(NotFoundException.class, () -> client.fetchAirport(icao));

        mockServer.verify();
    }

}
