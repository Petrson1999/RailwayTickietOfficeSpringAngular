package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.AddWagonRequest;
import com.railvayticketiffice.data.requests.AddWagonTypeRequest;
import com.railvayticketiffice.dto.WagonDTO;
import com.railvayticketiffice.dto.WagonTypeDTO;

import java.util.List;

public interface WagonService {

    List<WagonDTO> getAllWagonsDTO();

    List<WagonTypeDTO> getAllWagonTypesDTO();

    boolean addWagonType(AddWagonTypeRequest addWagonTypeRequest);

    boolean addWagon(AddWagonRequest addWagonRequest);

}
