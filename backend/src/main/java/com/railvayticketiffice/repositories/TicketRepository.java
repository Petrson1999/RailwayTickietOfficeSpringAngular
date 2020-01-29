package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByFlight(Flight flight);

    Ticket saveAndFlush(Ticket ticket);

}
