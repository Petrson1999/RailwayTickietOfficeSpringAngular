package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Locomotive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocomotiveRepository extends JpaRepository<Locomotive, Integer> {
}
