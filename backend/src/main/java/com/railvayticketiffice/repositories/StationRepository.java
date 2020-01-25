package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {
}
