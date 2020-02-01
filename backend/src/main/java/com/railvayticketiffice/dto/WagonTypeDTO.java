package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.entity.WagonType;

public class WagonTypeDTO {

    public WagonTypeDTO(int id, int numberOfSeats, int comfort, String name) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.comfort = comfort;
        this.name = name;
    }

    public WagonTypeDTO(WagonType wagonType) {
        this.id = wagonType.getId();
        this.numberOfSeats = wagonType.getNumberOfSeats();
        this.comfort = wagonType.getComfort();
        this.name = wagonType.getName();
    }

    private int id;

    private int numberOfSeats;

    private int comfort;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
