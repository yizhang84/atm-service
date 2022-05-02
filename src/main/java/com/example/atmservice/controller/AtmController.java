package com.example.atmservice.controller;

import com.example.atmservice.service.AtmService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/atms")
public class AtmController {
    private static final Logger LOG = LoggerFactory.getLogger(AtmController.class);

    private final AtmService service;

    public AtmController(final AtmService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get ATMs", notes = "Get ATMs for a given identification and url")
    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getAtms(@RequestParam final String url, @RequestParam final String identification) {
        LOG.info("Received GET request to get ATMs for identification [{}] from api [{}]", identification, url);

        return new ResponseEntity<>(service.getAtmsByIdentification(url, identification), HttpStatus.OK);
    }
}
