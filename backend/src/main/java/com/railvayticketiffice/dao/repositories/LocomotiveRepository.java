package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Locomotive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocomotiveRepository extends JpaRepository<Locomotive, Integer> {
}
