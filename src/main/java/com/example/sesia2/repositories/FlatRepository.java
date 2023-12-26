package com.example.sesia2.repositories;

import com.example.sesia2.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlatRepository extends JpaRepository<Flat, UUID> {
    Flat findByFlatCity(String flatCity);
}
