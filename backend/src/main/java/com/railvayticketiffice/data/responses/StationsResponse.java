package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.entity.Station;

import java.util.List;

public class StationsResponse extends BaseResponse {

    public StationsResponse() {
    }

    public StationsResponse(boolean succeeded, String message, List<Station> stations) {
        super(succeeded, message);
        this.stations = stations;
    }

    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
