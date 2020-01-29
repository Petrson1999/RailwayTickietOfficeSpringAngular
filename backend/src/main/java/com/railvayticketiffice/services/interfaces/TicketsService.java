package com.railvayticketiffice.services.interfaces;

import com.railvayticketiffice.data.requests.OrderRequest;

public interface TicketsService {

    boolean addTicket(OrderRequest orderRequest);

}
