package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.WagonDTO;

import java.util.List;

public class FlightSeatsResponse extends BaseResponse {

    public FlightSeatsResponse(boolean succeeded, String message, List<WagonDTO> wagonDTOS) {
        super(succeeded, message);
        this.wagonDTOS = wagonDTOS;
    }

    private List<WagonDTO> wagonDTOS;

    public List<WagonDTO> getWagonDTOS() {
        return wagonDTOS;
    }

    public void setWagonDTOS(List<WagonDTO> wagonDTOS) {
        this.wagonDTOS = wagonDTOS;
    }
}
