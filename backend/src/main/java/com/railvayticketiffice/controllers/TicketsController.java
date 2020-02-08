package com.railvayticketiffice.controllers;

import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.data.responses.BaseResponse;
import com.railvayticketiffice.data.responses.TicketDtoResponse;
import com.railvayticketiffice.dto.TicketDto;
import com.railvayticketiffice.services.interfaces.TicketsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/user/{userId}/actual")
    public ResponseEntity<TicketDtoResponse> getActualUserTickets(@PathVariable int userId){
        List<TicketDto> stations = ticketsService.getActualUserTickets(userId);
        TicketDtoResponse ticketDtoResponse = new TicketDtoResponse(true, "list of user actual tickets successfully received", stations);
        return new ResponseEntity<>(ticketDtoResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}/deprecated")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<TicketDtoResponse> getDeprecatedUserTickets(@PathVariable int userId){
        List<TicketDto> stations = ticketsService.getDeprecatedUserTickets(userId);
        TicketDtoResponse ticketDtoResponse = new TicketDtoResponse(true, "list of user actual tickets successfully received", stations);
        return new ResponseEntity<>(ticketDtoResponse, HttpStatus.OK);
    }

}
