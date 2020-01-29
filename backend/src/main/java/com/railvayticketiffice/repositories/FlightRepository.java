package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAll();

    Flight getOne(Integer id);

}
