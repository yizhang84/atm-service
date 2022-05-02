package com.example.atmservice.controller;

import com.example.atmservice.service.AtmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
class AtmControllerTest {
    private static final String API_URL = "open_api_url";
    private static final String IDENTIFICATION = "LFFFBC11";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtmService service;

    @Test
    public void shouldGetAtmForTheGivenIdentificationAndURL() throws Exception {
        when(service.getAtmsByIdentification(anyString(), anyString()))
                .thenReturn(getMockAtm());

        this.mockMvc.perform(get("/atms").param("url", API_URL).param("identification", IDENTIFICATION))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].Identification").value("LFFFBC11"));
    }

    private String getMockAtm() {
        return "[{\"Identification\":\"LFFFBC11\",\"SupportedCurrencies\":[\"GBP\"],\"Location\":{\"PostalAddress\":{\"AddressLine\":[\"1 VICTORIA ROAD;\"],\"StreetName\":\"1 VICTORIA ROAD\",\"TownName\":\"CONSETT\",\"CountrySubDivision\":[\"COUNTY DURHAM\"],\"Country\":\"GB\",\"PostCode\":\"DH8 5AE\"}}}]";
    }
}