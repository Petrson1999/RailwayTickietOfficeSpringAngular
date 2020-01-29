package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.responses.StationsResponse;
import com.railvayticketiffice.entity.Station;
import com.railvayticketiffice.services.interfaces.StationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin(origins = "http://localhost:4200")
public class StationsController {

    private static final Logger LOG = LoggerFactory.getLogger(StationsController.class);

    private StationsService stationsService;

    @Autowired
    public StationsController(StationsService stationsService) {
        this.stationsService = stationsService;
    }

    @GetMapping
    public ResponseEntity<StationsResponse> getAllStations(){
        List<Station> stations = stationsService.getAllStations();
        StationsResponse stationsResponse = new StationsResponse(true, "list of stations successfully received", stations);
        return new ResponseEntity<>(stationsResponse, HttpStatus.OK);
    }

}
