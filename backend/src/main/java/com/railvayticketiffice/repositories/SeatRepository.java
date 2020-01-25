package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
