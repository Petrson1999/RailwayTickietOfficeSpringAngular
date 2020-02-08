package com.railvayticketiffice.data.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddStationRequest {

    public AddStationRequest(){}

    public AddStationRequest(@NotNull @NotEmpty String stationName) {
        this.stationName = stationName;
    }

    @NotNull
    @NotEmpty
    private String stationName;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
