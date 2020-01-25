package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
}
