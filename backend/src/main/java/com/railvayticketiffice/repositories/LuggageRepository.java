package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuggageRepository extends JpaRepository<Luggage, Integer> {
}
