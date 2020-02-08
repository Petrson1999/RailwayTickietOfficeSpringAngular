package com.railvayticketiffice.data.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddWagonRequest {

    public AddWagonRequest(){}

    public AddWagonRequest(int typeId, int trainId, @NotNull @NotEmpty String name) {
        this.typeId = typeId;
        this.trainId = trainId;
        this.name = name;
    }

    private int typeId;

    private int trainId;

    @NotNull
    @NotEmpty
    private String name;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
}
