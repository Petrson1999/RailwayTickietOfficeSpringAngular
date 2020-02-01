package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Ticket;

import java.time.LocalDateTime;

public class TicketDto {

    public TicketDto(int id, int flightId, int userId, double cost, int seatId, String departureStation, String arrivalStation, String trainName, String wagonNumber, int seatNumber, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.flightId = flightId;
        this.userId = userId;
        this.cost = cost;
        this.seatId = seatId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.trainName = trainName;
        this.wagonNumber = wagonNumber;
        this.seatNumber = seatNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public TicketDto(Ticket ticket){
        this.id = ticket.getId();
        this.flightId = ticket.getFlight().getId();
        this.userId = ticket.getUser().getId();
        this.cost = ticket.getCost();
        this.seatId = ticket.getSeat().getId();
        this.departureStation = ticket.getFlight().getDepartureStation().getName();
        this.arrivalStation = ticket.getFlight().getArrivalStation().getName();
        this.trainName = ticket.getFlight().getTrain().getName();
        this.wagonNumber = ticket.getSeat().getWagon().getName();
        this.seatNumber = ticket.getSeat().getPlaceNumber();
        this.departureTime = ticket.getFlight().getDepartureTime();
        this.arrivalTime = ticket.getFlight().getArrivalTime();
    }

    private int id;

    private int flightId;

    private int userId;

    private double cost;

    private int seatId;

    private String departureStation;

    private String arrivalStation;

    private String trainName;

    private String wagonNumber;

    private int seatNumber;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(String wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
