package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    List<Station> findAll();

    Station findById(int stationId);

    Station saveAndFlush(Station station);

}
