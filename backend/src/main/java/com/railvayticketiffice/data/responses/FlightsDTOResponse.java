package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.FlightDTO;

import java.util.List;

public class FlightsDTOResponse extends BaseResponse {

    public FlightsDTOResponse(boolean succeeded, String message, List<FlightDTO> flightDtos) {
        super(succeeded, message);
        this.flightDtos = flightDtos;
    }

    private List<FlightDTO> flightDtos;

    public List<FlightDTO> getFlightDtos() {
        return flightDtos;
    }

    public void setFlightDtos(List<FlightDTO> flightDtos) {
        this.flightDtos = flightDtos;
    }
}
