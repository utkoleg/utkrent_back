package com.example.sesia2.services;

import com.example.sesia2.dto.FlatResponseDto;
import com.example.sesia2.entities.Flat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FlatService {

    List<FlatResponseDto> getFlats();

    Flat getFlatById(UUID id);

    Flat getFlatByCity(String city);

    Flat addFlat(String name, String city, int bed, int bath, int price, MultipartFile image);

    List<FlatResponseDto> getLikedFlats(String username);
}
