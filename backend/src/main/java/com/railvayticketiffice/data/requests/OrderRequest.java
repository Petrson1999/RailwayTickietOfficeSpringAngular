package com.railvayticketiffice.data.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderRequest {

    public OrderRequest(){
    }

    public OrderRequest(int flightId, int wagonId, int seatId, int userId, @NotNull @NotEmpty String email) {
        this.flightId = flightId;
        this.wagonId = wagonId;
        this.seatId = seatId;
        this.userId = userId;
        this.email = email;
    }

    private int flightId;

    private int wagonId;

    private int seatId;

    private int userId;

    @NotNull
    @NotEmpty
    private String email;

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getWagonId() {
        return wagonId;
    }

    public void setWagonId(int wagonId) {
        this.wagonId = wagonId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
