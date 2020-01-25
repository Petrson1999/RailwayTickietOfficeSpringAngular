package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
