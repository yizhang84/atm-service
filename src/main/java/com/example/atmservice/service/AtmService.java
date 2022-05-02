package com.example.atmservice.service;

import com.example.atmservice.exception.InputUrlFormatException;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class AtmService {
    private static final Logger LOG = LoggerFactory.getLogger(AtmService.class);

    private final RestTemplate restTemplate;

    public AtmService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get the ATM which has the identification as the given identification by querying the data returned from the open api.
     *
     * @param url            The Open API URL
     * @param identification
     * @return a string contains the atms information
     */
    public String getAtmsByIdentification(final String url, final String identification) {
        LOG.info("Request the open api [{}]", url);

        try {
            ResponseEntity<String> response
                    = restTemplate.getForEntity(url, String.class);

            LOG.info("Search the atms for identification [{}]", identification);

            // Using th JsonPath to query the json data returned from the open api
            return JsonPath.read(response.getBody(), "$..ATM[?(@.Identification == '" + identification + "')]").toString();
        } catch (final RestClientException | IllegalArgumentException e) {
            throw new InputUrlFormatException(e.getMessage(), e);
        }
    }
}
