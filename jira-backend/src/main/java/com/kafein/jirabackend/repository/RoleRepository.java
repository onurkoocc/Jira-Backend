package com.kafein.jirabackend.repository;

import com.kafein.jirabackend.model.ERole;
import com.kafein.jirabackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);

}
