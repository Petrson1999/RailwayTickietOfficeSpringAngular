package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.WagonTypeDTO;

import java.util.List;

public class WagonTypesResponse extends BaseResponse {

    public WagonTypesResponse(){}

    public WagonTypesResponse(boolean succeeded, String message, List<WagonTypeDTO> wagonTypes) {
        super(succeeded, message);
        this.wagonTypes = wagonTypes;
    }

    private List<WagonTypeDTO> wagonTypes;

    public List<WagonTypeDTO> getWagonTypes() {
        return wagonTypes;
    }

    public void setWagonTypes(List<WagonTypeDTO> wagonTypes) {
        this.wagonTypes = wagonTypes;
    }
}
