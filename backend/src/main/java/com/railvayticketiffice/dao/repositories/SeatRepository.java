package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Seat getOne(Integer id);

}
