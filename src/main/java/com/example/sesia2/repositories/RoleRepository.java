package com.example.sesia2.repositories;

import com.example.sesia2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}