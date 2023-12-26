package com.example.sesia2.services;

import com.example.sesia2.entities.Flat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlatService {

    List<Flat> getFlats();

    Optional<Flat> getFlatById(UUID id);

    Flat getFlatByCity(String city);

    Flat addFlat(Flat flat);
}
