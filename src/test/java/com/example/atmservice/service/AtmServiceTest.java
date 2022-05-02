package com.example.atmservice.service;

import com.example.atmservice.exception.InputUrlFormatException;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class AtmServiceTest {
    private static final String API_URL = "open_api_url";
    private static final String IDENTIFICATION = "LFFFBC11";

    @MockBean
    private RestTemplate restTemplate;

    private AtmService atmService;

    @BeforeEach
    public void initial() {
        atmService = new AtmService(restTemplate);
    }

    @Test
    public void shouldReturnAtmForValidUrlAndIdentification(){
        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(new ResponseEntity<>(getMockResponse(), HttpStatus.OK));

        final String atm = atmService.getAtmsByIdentification(API_URL, IDENTIFICATION);
        final String actualIdentification = JsonPath.read(atm, "$[0].Identification");

        assertEquals(IDENTIFICATION , actualIdentification);
    }

    @Test
    public void shouldThrowExceptionWhenInputUrlNotCorrect(){
        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenThrow(RestClientException.class);

        assertThrows(InputUrlFormatException.class, () -> {
            atmService.getAtmsByIdentification(API_URL, IDENTIFICATION);
        });
    }

    private String getMockResponse() {
        return "{\"meta\":{\"LastUpdated\":\"2022-04-07T15:08:14.846Z\"},\"data\":[{\"Brand\":[{\"BrandName\":\"Lloyds Bank\",\"ATM\":[{\"Identification\":\"LFFFBC11\",\"SupportedCurrencies\":[\"GBP\"],\"Location\":{\"PostalAddress\":{\"AddressLine\":[\"1 VICTORIA ROAD;\"],\"StreetName\":\"1 VICTORIA ROAD\",\"TownName\":\"CONSETT\",\"CountrySubDivision\":[\"COUNTY DURHAM\"],\"Country\":\"GB\",\"PostCode\":\"DH8 5AE\"}}},\n" +
                "{\"Identification\":\"LFFFAC11\",\"SupportedCurrencies\":[\"GBP\"],\"Location\":{\"PostalAddress\":{\"AddressLine\":[\"1 VICTORIA ROAD;\"],\"StreetName\":\"1 VICTORIA ROAD\",\"TownName\":\"CONSETT\",\"CountrySubDivision\":[\"COUNTY DURHAM\"],\"Country\":\"GB\",\"PostCode\":\"DH8 5AE\"}}}]}]}]}";
    }
}