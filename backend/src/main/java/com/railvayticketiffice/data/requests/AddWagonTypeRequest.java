package com.railvayticketiffice.data.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddWagonTypeRequest {

    public AddWagonTypeRequest(){}

    public AddWagonTypeRequest(@NotNull @NotEmpty String name, int seatsCount) {
        this.name = name;
        this.seatsCount = seatsCount;
    }

    @NotNull
    @NotEmpty
    private String name;

    private int seatsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }
}
