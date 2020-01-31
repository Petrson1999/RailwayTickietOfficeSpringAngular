package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
}
