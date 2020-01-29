package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.repositories.FlightRepository;
import com.railvayticketiffice.repositories.SeatRepository;
import com.railvayticketiffice.repositories.TicketRepository;
import com.railvayticketiffice.repositories.UserRepository;
import com.railvayticketiffice.services.interfaces.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketsServiceImpl implements TicketsService {

    @Autowired
    public TicketsServiceImpl(TicketRepository ticketRepository, FlightRepository flightRepository, UserRepository userRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    private TicketRepository ticketRepository;

    private FlightRepository flightRepository;

    private UserRepository userRepository;

    private SeatRepository seatRepository;


    @Override
    public boolean addTicket(OrderRequest orderRequest) {
        Flight flight = flightRepository.getOne(orderRequest.getFlightId());
        User user = userRepository.getOne(orderRequest.getUserId());
        Seat seat = seatRepository.getOne(orderRequest.getSeatId());
        if (flight == null || user == null || seat == null) {
            return false;
        }

        Ticket ticket = new Ticket(flight, user, flight.getCost(), seat);

        ticket = ticketRepository.saveAndFlush(ticket);

        return ticket != null;
    }

}
