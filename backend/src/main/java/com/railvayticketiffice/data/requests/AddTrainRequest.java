package com.railvayticketiffice.data.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddTrainRequest {

    public AddTrainRequest(){}

    public AddTrainRequest(@NotNull @NotEmpty String trainName) {
        this.trainName = trainName;
    }

    @NotNull
    @NotEmpty
    private String trainName;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
