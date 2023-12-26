package com.example.sesia2.services.impl;

import com.example.sesia2.entities.Flat;
import com.example.sesia2.repositories.FlatRepository;
import com.example.sesia2.services.FlatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlatServiceImpl implements FlatService {

    private final FlatRepository flatRepository;
    @Override
    public List<Flat> getFlats() {
        return flatRepository.findAll();
    }

    @Override
    public Optional<Flat> getFlatById(UUID id) {
        return flatRepository.findById(id);
    }

    @Override
    public Flat getFlatByCity(String city) {
        return flatRepository.findByFlatCity(city);
    }

    @Override
    public Flat addFlat(Flat flat) {
        return flatRepository.save(flat);
    }
}
