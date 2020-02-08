package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.AddWagonRequest;
import com.railvayticketiffice.data.requests.AddWagonTypeRequest;
import com.railvayticketiffice.data.responses.BaseResponse;
import com.railvayticketiffice.data.responses.TrainsResponse;
import com.railvayticketiffice.data.responses.WagonTypesResponse;
import com.railvayticketiffice.data.responses.WagonsResponse;
import com.railvayticketiffice.dto.TrainDTO;
import com.railvayticketiffice.dto.WagonDTO;
import com.railvayticketiffice.dto.WagonTypeDTO;
import com.railvayticketiffice.services.interfaces.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wagons")
@CrossOrigin(origins = "http://localhost:4200")
public class WagonController {

    private WagonService wagonService;

    @Autowired
    public WagonController(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<WagonsResponse> getAllWagons() {
        List<WagonDTO> wagons = wagonService.getAllWagonsDTO();
        WagonsResponse wagonsResponse = new WagonsResponse(true, "list wagons successfully received", wagons);
        return new ResponseEntity<>(wagonsResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/types")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<WagonTypesResponse> getAllWagonTypes() {
        List<WagonTypeDTO> wagonTypes = wagonService.getAllWagonTypesDTO();
        WagonTypesResponse wagonsResponse = new WagonTypesResponse(true, "list wagons successfully received", wagonTypes);
        return new ResponseEntity<>(wagonsResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/types")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> addWagonType(@Valid @RequestBody AddWagonTypeRequest addWagonTypeRequest) {
        boolean success = wagonService.addWagonType(addWagonTypeRequest);
        String message;
        if(success){
            message = "wagon type successfully added";
        }else {
            message = "wagon type not added";
        }
        BaseResponse baseResponse = new BaseResponse(success, message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> addWagon(@Valid @RequestBody AddWagonRequest addWagonRequest) {
        boolean success = wagonService.addWagon(addWagonRequest);
        String message;
        if(success){
            message = "wagon successfully added";
        }else {
            message = "wagon not added";
        }
        BaseResponse baseResponse = new BaseResponse(success, message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


}
