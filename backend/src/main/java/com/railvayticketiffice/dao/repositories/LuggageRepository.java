package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage, Integer> {
}
