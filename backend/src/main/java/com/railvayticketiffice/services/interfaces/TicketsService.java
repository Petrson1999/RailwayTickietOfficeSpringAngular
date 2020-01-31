package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.dto.TicketDto;

import java.util.List;

public interface TicketsService {

    boolean addTicket(OrderRequest orderRequest);

    List<TicketDto> getActualUserTickets(int userId);

    List<TicketDto> getDeprecatedUserTickets(int userId);

}
