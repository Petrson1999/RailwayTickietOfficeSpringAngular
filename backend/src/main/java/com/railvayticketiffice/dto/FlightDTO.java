package com.railvayticketiffice.dto;

import java.time.LocalDateTime;

public class FlightDTO {

    public FlightDTO() {
    }

    public FlightDTO(int id, StationDTO departureStationDTO, StationDTO arrivalStationDTO, String trainName, LocalDateTime formatedDepartureTime, LocalDateTime formatedArrivalTime, double cost, String name, int freeSeatNumber) {
        this.id = id;
        this.departureStationDTO = departureStationDTO;
        this.arrivalStationDTO = arrivalStationDTO;
        this.trainName = trainName;
        this.formatedDepartureTime = formatedDepartureTime;
        this.formatedArrivalTime = formatedArrivalTime;
        this.cost = cost;
        this.name = name;
        this.freeSeatNumber = freeSeatNumber;
    }

    private int id;

    private StationDTO departureStationDTO;

    private StationDTO arrivalStationDTO;

    private String trainName;

    private LocalDateTime formatedDepartureTime;

    private LocalDateTime formatedArrivalTime;

    private double cost;

    private String name;

    private int freeSeatNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StationDTO getDepartureStationDTO() {
        return departureStationDTO;
    }

    public void setDepartureStationDTO(StationDTO departureStationDTO) {
        this.departureStationDTO = departureStationDTO;
    }

    public StationDTO getArrivalStationDTO() {
        return arrivalStationDTO;
    }

    public void setArrivalStationDTO(StationDTO arrivalStationDTO) {
        this.arrivalStationDTO = arrivalStationDTO;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public LocalDateTime getFormatedDepartureTime() {
        return formatedDepartureTime;
    }

    public void setFormatedDepartureTime(LocalDateTime formatedDepartureTime) {
        this.formatedDepartureTime = formatedDepartureTime;
    }

    public LocalDateTime getFormatedArrivalTime() {
        return formatedArrivalTime;
    }

    public void setFormatedArrivalTime(LocalDateTime formatedArrivalTime) {
        this.formatedArrivalTime = formatedArrivalTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeSeatNumber() {
        return freeSeatNumber;
    }

    public void setFreeSeatNumber(int freeSeatNumber) {
        this.freeSeatNumber = freeSeatNumber;
    }
}
