package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.AddFlightRequest;
import com.railvayticketiffice.data.requests.FlightSearchRequest;
import com.railvayticketiffice.data.responses.BaseResponse;
import com.railvayticketiffice.data.responses.FlightSeatsResponse;
import com.railvayticketiffice.data.responses.FlightsDTOResponse;
import com.railvayticketiffice.data.responses.FlightsResponse;
import com.railvayticketiffice.dto.FlightDTO;
import com.railvayticketiffice.dto.WagonDTO;
import com.railvayticketiffice.services.interfaces.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:4200")
public class FlightController {

    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(path = "/search")
    public ResponseEntity<FlightsDTOResponse> getFlightsBySearch(@Valid @RequestBody FlightSearchRequest flightSearchRequest) {
        List<FlightDTO> flightDtos = flightService.getFlightsBySearch(flightSearchRequest);
        FlightsDTOResponse flightsDTOResponse = new FlightsDTOResponse(true, "list of flights dto successfully received", flightDtos);
        return new ResponseEntity<>(flightsDTOResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{flightId}/seats")
    public ResponseEntity<FlightSeatsResponse> getFlightSeats(@PathVariable int flightId) {
        List<WagonDTO> wagonDTOS = flightService.getFlightSeats(flightId);
        FlightSeatsResponse flightSeatsResponse = new FlightSeatsResponse(true, "list  flight seats successfully received", wagonDTOS);
        return new ResponseEntity<>(flightSeatsResponse, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> addFlights(@Valid @RequestBody AddFlightRequest addFlightRequest) {
        boolean success = flightService.addNewFlight(addFlightRequest);
        String message;
        if(success){
            message = "flight successfully added";
        }else {
            message = "flight not added";
        }
        BaseResponse baseResponse = new BaseResponse(success, message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FlightsResponse> getAllFlights() {
        List<FlightDTO> flightDtos = flightService.getAllDto();
        FlightsResponse flightsResponse = new FlightsResponse(true, "list  flights successfully received", flightDtos);
        return new ResponseEntity<>(flightsResponse, HttpStatus.OK);
    }

}
