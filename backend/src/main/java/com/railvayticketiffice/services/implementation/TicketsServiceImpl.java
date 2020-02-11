package com.railvayticketiffice.services.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.railvayticketiffice.dao.repositories.*;
import com.railvayticketiffice.data.requests.OrderRequest;
import com.railvayticketiffice.dto.TicketDto;
import com.railvayticketiffice.entity.*;
import com.railvayticketiffice.services.interfaces.EmailService;
import com.railvayticketiffice.services.interfaces.TicketsService;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TicketsServiceImpl implements TicketsService {

    private static final Logger LOG = LoggerFactory.getLogger(TicketsServiceImpl.class);

    @Autowired
    public TicketsServiceImpl(FlightRepository flightRepository, UserRepository userRepository, SeatRepository seatRepository, EntityManager entityManager, EmailService emailService) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.entityManager = entityManager;
        this.emailService = emailService;
    }


    private FlightRepository flightRepository;

    private UserRepository userRepository;

    private SeatRepository seatRepository;

    private EntityManager entityManager;

    private EmailService emailService;


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

            createPDFTicket(ticket);
            emailService.sendEmail(user, ticket, orderRequest.getEmail());

            return true;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(String.format("not enough money", ex));
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

    private void createPDFTicket(Ticket ticket) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\T34-85\\IdeaProjects\\RailwayTickietOfficeSpringAngular\\backend\\src\\main\\resources\\ticket.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            LOG.error(String.format("pdf creation error", e));
        }
        document.open();

        Anchor anchorTarget = new Anchor(
                "flight: " + ticket.getFlight().getName() + "\n" +
                        "passenger: " + ticket.getUser().getName() + " " + ticket.getUser().getSurname() + "\n" +
                        "wagon: " + ticket.getSeat().getWagon().getName() + "\n" +
                        "wagon type: " + ticket.getSeat().getWagon().getWagonType().getName() + "\n" +
                        "seat: " + ticket.getSeat().getPlaceNumber() + "\n" +
                        "cost: " + ticket.getCost());
        anchorTarget.setName("BackToTop");
        Paragraph paragraph1 = new Paragraph();

        paragraph1.setSpacingBefore(50);

        paragraph1.add(anchorTarget);
        try {
            document.add(paragraph1);
        } catch (DocumentException e) {
            LOG.error(String.format("pdf open error", e));
        }

        document.close();


    }

}
