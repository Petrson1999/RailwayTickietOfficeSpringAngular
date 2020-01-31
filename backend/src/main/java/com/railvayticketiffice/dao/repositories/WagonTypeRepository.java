package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.WagonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WagonTypeRepository extends JpaRepository<WagonType, Integer> {
}
