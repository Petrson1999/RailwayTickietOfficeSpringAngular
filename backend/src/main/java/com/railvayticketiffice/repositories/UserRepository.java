package com.railvayticketiffice.repositories;

import com.railvayticketiffice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
