package com.example.sesia2.mapper;

import com.example.sesia2.dto.FlatResponseDto;
import com.example.sesia2.entities.Flat;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Service
public class FlatResponseMapper implements Function<Flat, FlatResponseDto> {
    @Override
    public FlatResponseDto apply(Flat flat) {
        return new FlatResponseDto(
                flat.getId(),
                flat.getFlatName(),
                flat.getFlatCity(),
                flat.getFlatBed(),
                flat.getFlatBath(),
                flat.getPrice(),
                ServletUriComponentsBuilder.
                        fromCurrentContextPath().build().toUriString() + "/api/v1/images/" + flat.getImage().getId()
        );
    }
}
