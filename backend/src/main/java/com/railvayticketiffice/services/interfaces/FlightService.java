package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.AddFlightRequest;
import com.railvayticketiffice.data.requests.FlightSearchRequest;
import com.railvayticketiffice.dto.FlightDTO;
import com.railvayticketiffice.dto.WagonDTO;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getAllDto();

    List<FlightDTO> getFlightsBySearch(FlightSearchRequest flightSearchRequest);

    List<WagonDTO> getFlightSeats(int flightId);

    boolean addNewFlight(AddFlightRequest flightForm);

}
