package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.User;
import com.railvayticketiffice.dao.repositories.FlightRepository;
import com.railvayticketiffice.dao.repositories.SeatRepository;
import com.railvayticketiffice.dao.repositories.TicketRepository;
import com.railvayticketiffice.dao.repositories.UserRepository;
import com.railvayticketiffice.services.interfaces.TicketsService;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;


@Service
public class TicketsServiceImpl implements TicketsService {

    @Autowired
    public TicketsServiceImpl(TicketRepository ticketRepository, FlightRepository flightRepository, UserRepository userRepository, SeatRepository seatRepository, EntityManager entityManager) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.entityManager = entityManager;
    }

    private TicketRepository ticketRepository;

    private FlightRepository flightRepository;

    private UserRepository userRepository;

    private SeatRepository seatRepository;

    private EntityManager entityManager;

    @Override
    public boolean addTicket(OrderRequest orderRequest) {
        Flight flight = flightRepository.findById(orderRequest.getFlightId());
        User user = userRepository.getOne(orderRequest.getUserId());
        Seat seat = seatRepository.getOne(orderRequest.getSeatId());
        if (flight == null || user == null || seat == null) {
            return false;
        }

        Ticket ticket = new Ticket(flight, user, flight.getCost(), seat);

        if(user.getFunds() < ticket.getCost()){
            return false;
        }else {
            user.setFunds(user.getFunds() - ticket.getCost());
        }


        Transaction transaction = null;
        Session session = entityManager.unwrap(Session.class);

        try {
            transaction = session.beginTransaction();
            session.save(ticket);
            session.saveOrUpdate(user);
            session.flush();
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
        return false;






        /*ticket = ticketRepository.saveAndFlush(ticket);

        return ticket != null;*/
    }

}
