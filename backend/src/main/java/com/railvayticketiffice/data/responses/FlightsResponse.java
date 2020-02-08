package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.FlightDTO;

import java.util.List;

public class FlightsResponse extends BaseResponse {

    public FlightsResponse(boolean succeeded, String message, List<FlightDTO> flightDTOS) {
        super(succeeded, message);
        this.flightDTOS = flightDTOS;
    }

    private List<FlightDTO> flightDTOS;

    public List<FlightDTO> getFlightDTOS() {
        return flightDTOS;
    }

    public void setFlightDTOS(List<FlightDTO> flightDTOS) {
        this.flightDTOS = flightDTOS;
    }
}
