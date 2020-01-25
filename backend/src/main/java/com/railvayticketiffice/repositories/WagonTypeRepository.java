package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.WagonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WagonTypeRepository extends JpaRepository<WagonType, Integer> {
}
