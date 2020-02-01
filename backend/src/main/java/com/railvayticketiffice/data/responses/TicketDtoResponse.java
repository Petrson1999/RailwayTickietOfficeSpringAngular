package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.dto.TicketDto;

import java.util.List;

public class TicketDtoResponse extends BaseResponse{

    public TicketDtoResponse(boolean succeeded, String message, List<TicketDto> ticketDtos) {
        super(succeeded, message);
        this.ticketDtos = ticketDtos;
    }

    private List<TicketDto> ticketDtos;

    public List<TicketDto> getTicketDtos() {
        return ticketDtos;
    }

    public void setTicketDtos(List<TicketDto> ticketDtos) {
        this.ticketDtos = ticketDtos;
    }
}
