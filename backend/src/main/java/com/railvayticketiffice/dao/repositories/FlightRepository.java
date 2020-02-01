package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAll();

    Flight getOne(Integer id);

    Flight findById(int id);

    Flight saveAndFlush(Flight flight);

}
