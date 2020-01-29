package com.railvayticketiffice.data.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FlightSearchRequest {

    public FlightSearchRequest(){}

    public FlightSearchRequest(int departureStationId, int arrivalStationId, String dateTime) {
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.dateTime = dateTime;
    }

    private int departureStationId;

    private int arrivalStationId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private String dateTime;

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
