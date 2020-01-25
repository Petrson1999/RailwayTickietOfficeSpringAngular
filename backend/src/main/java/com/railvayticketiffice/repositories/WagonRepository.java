package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagonRepository extends JpaRepository<Wagon, Integer> {
}
