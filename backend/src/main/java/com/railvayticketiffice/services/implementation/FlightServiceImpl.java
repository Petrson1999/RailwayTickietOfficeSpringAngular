package com.railvayticketiffice.services.implementation;

import com.railvayticketiffice.data.requests.FlightSearchRequest;
import com.railvayticketiffice.dto.*;
import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Seat;
import com.railvayticketiffice.entity.Ticket;
import com.railvayticketiffice.entity.Wagon;
import com.railvayticketiffice.repositories.FlightRepository;
import com.railvayticketiffice.repositories.TicketRepository;
import com.railvayticketiffice.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }

    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;

    @Override
    public List<FlightDTO> getAllDto() {
        List<Flight> flights = flightRepository.findAll();
        if (flights == null) {
            return null;
        }

        List<FlightDTO> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDTO flightDto = new FlightDTO(
                    flight.getId(),
                    new StationDTO(flight.getDepartureStation()),
                    new StationDTO(flight.getArrivalStation()),
                    flight.getTrain().getName(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getCost(),
                    flight.getName(),
                    getAllFreeSeatsNumber(flight)
            );
            flightDtos.add(flightDto);
        }
        return flightDtos;

    }

    @Override
    public List<FlightDTO> getFlightsBySearch(FlightSearchRequest flightSearchRequest) {
        List<FlightDTO> flightDtos = getAllDto();
        if (flightDtos == null) {
            return null;
        }
        flightDtos = flightDtos.stream().filter(x -> x.getDepartureStationDTO().getId() == flightSearchRequest.getDepartureStationId()
                && x.getArrivalStationDTO().getId() == flightSearchRequest.getArrivalStationId()).collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dateTime = LocalDateTime.parse(flightSearchRequest.getDateTime(), formatter);

        return flightDtos.stream().filter(x ->
                x.getFormatedDepartureTime().getYear() == dateTime.getYear() &&
                        (x.getFormatedDepartureTime().getMonth() == dateTime.getMonth() &&
                        x.getFormatedDepartureTime().getDayOfMonth() == dateTime.getDayOfMonth() &&
                        x.getFormatedDepartureTime().getHour() >= dateTime.getHour()) ||
                        (x.getFormatedDepartureTime().getYear() == dateTime.getYear() &&
                                x.getFormatedDepartureTime().getMonth() == dateTime.getMonth() &&
                                x.getFormatedDepartureTime().getDayOfMonth() == dateTime.getDayOfMonth() &&
                                x.getFormatedDepartureTime().getHour() == dateTime.getHour() &&
                                x.getFormatedDepartureTime().getMinute() >= dateTime.getMinute())
        ).collect(Collectors.toList());
    }

    @Override
    public List<WagonDTO> getFlightSeats(int flightId) {
        List<WagonDTO> wagonDtos = new ArrayList<>();

        Flight flight = flightRepository.getOne(flightId);

        if (flight == null) {
            return null;
        }

        List<Wagon> wagons = new ArrayList<>(flight.getTrain().getWagons());

        List<Ticket> tickets = ticketRepository.findByFlight(flight);

        for (Wagon wagon : wagons) {
            List<Seat> wagonAllSeats = new ArrayList<>(wagon.getSeats());
            List<Seat> wagonFreeSeats = null;
            wagonFreeSeats = getFreeSeatsInWagon(tickets, wagonAllSeats);
            List<SeatDTO> seats = new ArrayList<>();
            for(Seat seat: wagonFreeSeats){
                seats.add(new SeatDTO(seat.getId(), seat.getWagon().getId(), seat.getPlaceNumber()));
            }
                wagonDtos.add(new WagonDTO(wagon,  seats));
        }

        return wagonDtos;
    }

    private int getAllFreeSeatsNumber(Flight flight) {
        int number = 0;
        SeatsDTO seatsDto = getFreeSeats(flight);
        if (seatsDto == null) {
            return 0;
        }
        for (Map.Entry<Wagon, List<Seat>> wagonSeats : seatsDto.getFreeSeats().entrySet()) {
            number += wagonSeats.getValue().size();
        }
        return number;
    }

    private SeatsDTO getFreeSeats(Flight flight) {
        if (flight == null) {
            return null;
        }

        SeatsDTO seatDto = new SeatsDTO();

        List<Wagon> wagons = new ArrayList<>(flight.getTrain().getWagons());

        if (wagons.size() == 0) {
            return null;
        }

        List<Ticket> tickets = new ArrayList<>(flight.getTickets());

        Map<Wagon, List<Seat>> freeSeats = new HashMap<>();

        for (Wagon wagon : wagons) {
            List<Seat> wagonFreeSeats = null;
            if (wagon.getSeats() != null) {
                wagonFreeSeats = getFreeSeatsInWagon(tickets, new ArrayList<>(wagon.getSeats()));
            }
            if (wagonFreeSeats != null) {
                freeSeats.put(wagon, wagonFreeSeats);
            }
        }

        seatDto.setFreeSeats(freeSeats);

        return seatDto;
    }

    private List<Seat> getFreeSeatsInWagon(List<Ticket> busyTickets, List<Seat> wagonSeat) {
        List<Seat> freeWagonSeat = new ArrayList<>();
        if (wagonSeat != null) {
            for (Seat seat : wagonSeat) {
                if (busyTickets.stream().noneMatch(x -> x.getSeat().getId().equals(seat.getId())) && freeWagonSeat.stream().noneMatch(x -> x.equals(seat))) {
                    freeWagonSeat.add(seat);
                }
            }
            return freeWagonSeat;
        } else return null;
    }


}
