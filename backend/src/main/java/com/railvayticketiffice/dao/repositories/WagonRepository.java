package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonRepository extends JpaRepository<Wagon, Integer> {
}
