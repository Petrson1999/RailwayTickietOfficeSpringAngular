package com.railvayticketiffice.dto;

import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.entity.WagonType;

import java.util.List;

public class WagonDTO {

    public WagonDTO(Wagon wagon , List<SeatDTO> seats){
        WagonType wagonType = wagon.getWagonType();
        this.setId(wagon.getId());
        this.setTrainId(wagon.getTrain().getId());
        this.setName(wagon.getName());
        this.wagonType = new WagonTypeDTO( wagonType.getId(), wagonType.getNumberOfSeats(), wagonType.getComfort(), wagonType.getName());
        this.seats = seats;
    }

    private int id;

    private int trainId;

    private String name;

    private WagonTypeDTO wagonType;

    private List<SeatDTO> seats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WagonTypeDTO getWagonType() {
        return wagonType;
    }

    public void setWagonType(WagonTypeDTO wagonType) {
        this.wagonType = wagonType;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

}
