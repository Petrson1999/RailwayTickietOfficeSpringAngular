package com.railvayticketiffice.data.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddFlightRequest {

    private int departureStationId;

    private int arrivalStationId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private String departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private String arrivalDate;

    private int trainId;

    private double cost;

    public AddFlightRequest(){}

    public AddFlightRequest(int departureStationId, int arrivalStationId, String departureDate, String arrivalDate, int trainId, double cost) {
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.trainId = trainId;
        this.cost = cost;
    }

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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
