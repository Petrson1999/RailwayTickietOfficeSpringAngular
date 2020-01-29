package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Station;

public class StationDTO {

    public StationDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StationDTO(Station station) {
        this.id = station.getId();
        this.name = station.getName();
    }

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
