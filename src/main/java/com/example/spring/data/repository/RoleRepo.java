package com.example.spring.data.repository;

import com.example.spring.data.model.ERole;
import com.example.spring.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
