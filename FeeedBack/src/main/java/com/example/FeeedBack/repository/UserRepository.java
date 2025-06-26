package com.example.FeeedBack.repository;

import com.example.FeeedBack.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);
}
