package com.kafein.jirabackend.repository;

import com.kafein.jirabackend.model.Role;
import com.kafein.jirabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Override
    boolean existsById(Integer integer);

    User findByRole(Role role);
}