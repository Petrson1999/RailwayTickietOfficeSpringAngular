package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.dao.repositories.TrainRepository;
import com.railvayticketiffice.data.requests.AddTrainRequest;
import com.railvayticketiffice.dto.TrainDTO;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.services.interfaces.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    private TrainRepository trainRepository;


    @Override
    public List<TrainDTO> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        List<TrainDTO> trainDTOS = new ArrayList<>();
        for (Train train : trains) {
            trainDTOS.add(new TrainDTO(train.getId(), train.getName()));
        }
        return trainDTOS;
    }

    @Override
    public boolean addTrain(AddTrainRequest addTrainRequest) {
        Train train = new Train();
        train.setName(addTrainRequest.getTrainName());
        return trainRepository.saveAndFlush(train) != null;
    }
}
