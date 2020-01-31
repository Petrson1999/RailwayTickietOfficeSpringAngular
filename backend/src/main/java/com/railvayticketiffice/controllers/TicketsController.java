package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.data.responses.BaseResponse;
import com.railvayticketiffice.services.interfaces.TicketsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketsController {

    private static final Logger LOG = LoggerFactory.getLogger(TicketsController.class);

    private TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @PostMapping(path = "/order")
    public ResponseEntity<BaseResponse> getFlightsBySearch(@RequestBody OrderRequest orderRequest) {
        boolean success = ticketsService.addTicket(orderRequest);
        String message;
        if(success){
            message="ticket successfully purchased";
        }else {
            message="failed to buy a ticket";
        }
        BaseResponse baseResponse = new BaseResponse(success, message);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
