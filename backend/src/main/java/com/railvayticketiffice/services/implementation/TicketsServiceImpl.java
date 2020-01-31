package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.dao.repositories.*;
import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.dto.FlightDTO;
import com.railvayticketiffice.dto.TicketDto;
import com.railvayticketiffice.entity.*;
import com.railvayticketiffice.services.interfaces.FlightService;
import com.railvayticketiffice.services.interfaces.TicketsService;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TicketsServiceImpl implements TicketsService {

    @Autowired
    public TicketsServiceImpl(FlightRepository flightRepository, UserRepository userRepository, SeatRepository seatRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.entityManager = entityManager;
    }


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

        if (user.getFunds() < ticket.getCost()) {
            return false;
        } else {
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
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public List<TicketDto> getActualUserTickets(int userId) {
        LocalDateTime now = LocalDateTime.now();
        return getUserTickets(userId).stream().filter(x -> x.getDepartureTime().isAfter(now)).collect(Collectors.toList());

    }

    @Override
    public List<TicketDto> getDeprecatedUserTickets(int userId) {
        LocalDateTime now = LocalDateTime.now();
        return getUserTickets(userId).stream().filter(x -> x.getDepartureTime().isBefore(now)).collect(Collectors.toList());

    }

    public List<TicketDto> getUserTickets(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else return null;


        List<Ticket> userAllTickets = new ArrayList<>(user.getTickets());
        List<TicketDto> userAllTicketsDto = new ArrayList<>();


        for (Ticket ticket : userAllTickets) {
            TicketDto ticketDto = new TicketDto(ticket);
            userAllTicketsDto.add(ticketDto);
        }
        return userAllTicketsDto;
    }

}
