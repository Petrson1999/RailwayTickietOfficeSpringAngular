package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Seat;

public class SeatDTO {

    public SeatDTO(int id, int wagonId, int placeNumber) {
        this.id = id;
        this.wagonId = wagonId;
        this.placeNumber = placeNumber;
    }

    public SeatDTO(Seat seat) {
        this.id = seat.getId();
        this.wagonId = seat.getWagon().getId();
        this.placeNumber = seat.getPlaceNumber();
    }

    private int id;

    private int wagonId;

    private int placeNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWagonId() {
        return wagonId;
    }

    public void setWagonId(int wagonId) {
        this.wagonId = wagonId;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}
