package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.AddTrainRequest;
import com.railvayticketiffice.dto.TrainDTO;

import java.util.List;

public interface TrainService {

    List<TrainDTO> getAllTrains();

    boolean addTrain(AddTrainRequest addTrainRequest);

}
