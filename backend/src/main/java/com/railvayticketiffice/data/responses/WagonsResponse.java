package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.WagonDTO;

import java.util.List;

public class WagonsResponse extends BaseResponse{

    public WagonsResponse() {
    }

    public WagonsResponse(boolean succeeded, String message, List<WagonDTO> wagons) {
        super(succeeded, message);
        this.wagons = wagons;
    }

    private List<WagonDTO> wagons;

    public List<WagonDTO> getWagons() {
        return wagons;
    }

    public void setWagons(List<WagonDTO> wagons) {
        this.wagons = wagons;
    }
}
