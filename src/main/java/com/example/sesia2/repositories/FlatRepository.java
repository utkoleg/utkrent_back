package com.example.sesia2.repositories;

import com.example.sesia2.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FlatRepository extends JpaRepository<Flat, UUID> {
    Flat findByFlatCity(String flatCity);

    Flat findFlatById(UUID id);

//    @Query("SELECT f FROM Flat f JOIN f.users u WHERE u.username = :username")
//    List<Flat> findLikedFlatsByUsername(@Param("username") String username);

}
