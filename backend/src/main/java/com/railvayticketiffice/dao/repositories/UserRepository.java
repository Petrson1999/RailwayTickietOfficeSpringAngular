package com.railvayticketiffice.dao.repositories;

import com.railvayticketiffice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    Optional<User> findById(Integer id);

    User saveAndFlush(User user);

}
