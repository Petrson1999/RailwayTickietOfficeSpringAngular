package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Integer> {

    List<Station> findAll();

}
