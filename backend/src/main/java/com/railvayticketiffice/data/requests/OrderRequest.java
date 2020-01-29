package com.railvayticketiffice.data.requests;

public class OrderRequest {

    public OrderRequest(){
    }

    public OrderRequest(int flightId, int wagonId, int seatId, int userId) {
        this.flightId = flightId;
        this.wagonId = wagonId;
        this.seatId = seatId;
        this.userId = userId;
    }

    private int flightId;

    private int wagonId;

    private int seatId;

    private int userId;

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
}
