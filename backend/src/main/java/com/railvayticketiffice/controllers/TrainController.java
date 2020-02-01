package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.AddStationRequest;
import com.railvayticketiffice.data.requests.AddTrainRequest;
import com.railvayticketiffice.data.responses.BaseResponse;
import com.railvayticketiffice.data.responses.TrainsResponse;
import com.railvayticketiffice.dto.TrainDTO;
import com.railvayticketiffice.entity.Train;
import com.railvayticketiffice.services.interfaces.TrainService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.*;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "http://localhost:4200")
public class TrainController {

    private static final Logger LOG = getLogger(TrainController.class);

    private TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TrainsResponse> getAllFlights() {
        List<TrainDTO> trains = trainService.getAllTrains();
        TrainsResponse trainsResponse = new TrainsResponse(true, "list trains successfully received", trains);
        return new ResponseEntity<>(trainsResponse, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> addTrain(@Valid @RequestBody AddTrainRequest addTrainRequest) {
        boolean success = trainService.addTrain(addTrainRequest);
        String message;
        if(success){
            message = "train successfully added";
        }else {
            message = "train not added";
        }
        BaseResponse baseResponse = new BaseResponse(success, message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
