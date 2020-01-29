package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Wagon;

import java.util.List;
import java.util.Map;

public class SeatsDTO {

    private Map<Wagon, List<Seat>> freeSeats;

    public Map<Wagon, List<Seat>> getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Map<Wagon, List<Seat>> freeSeats) {
        this.freeSeats = freeSeats;
    }
}
