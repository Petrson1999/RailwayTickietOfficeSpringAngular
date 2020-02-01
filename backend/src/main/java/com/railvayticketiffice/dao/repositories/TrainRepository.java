package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {

    List<Train> findAll();

    Train findById(int trainId);

}
