package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Flight;
import com.railvayticketiffice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    List<Ticket> findByFlight(Flight flight);

    Ticket saveAndFlush(Ticket ticket);

}
