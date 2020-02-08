package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.TrainDTO;
import com.railvayticketiffice.entity.Train;

import java.util.List;

public class TrainsResponse extends BaseResponse {

    public TrainsResponse(boolean succeeded, String message, List<TrainDTO> trains) {
        super(succeeded, message);
        this.trains = trains;
    }

    private List<TrainDTO> trains;

    public List<TrainDTO> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainDTO> trains) {
        this.trains = trains;
    }
}
