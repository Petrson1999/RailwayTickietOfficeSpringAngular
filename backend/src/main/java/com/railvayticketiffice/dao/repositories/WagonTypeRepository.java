package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.WagonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagonTypeRepository extends JpaRepository<WagonType, Integer> {

    List<WagonType> findAll();

    WagonType saveAndFlush(WagonType wagonType);

    WagonType findById(int wagonId);

}
