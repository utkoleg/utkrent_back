package com.example.sesia2.services.impl;

import com.example.sesia2.dto.FlatResponseDto;
import com.example.sesia2.entities.Flat;
import com.example.sesia2.repositories.FlatRepository;
import com.example.sesia2.services.FlatService;
import com.example.sesia2.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlatServiceImpl implements FlatService {

    private final FlatRepository flatRepository;
    @Override
    public List<FlatResponseDto> getFlats() {
        return flatRepository.findAll().stream().map(flat -> new FlatResponseDto(flat.getId(), flat.getFlatName(), flat.getFlatCity(),
                flat.getFlatBed(), flat.getFlatBath(), flat.getPrice(), ServletUriComponentsBuilder.
                fromCurrentContextPath().build().toUriString() + "/api/v1/images/" + flat.getImage().getId())).toList();
    }

    @Override
    public Flat getFlatById(UUID id) {
        return flatRepository.findFlatById(id);
    }

    public Flat saveFlat(Flat flat){
        return flatRepository.save(flat);
    }
    @Override
    public Flat getFlatByCity(String city) {
        return flatRepository.findByFlatCity(city);
    }

    @Override
    public Flat addFlat(String name, String city, int bed, int bath, int price, MultipartFile image) {
        Flat flat = new Flat();
        flat.setFlatName(name);
        flat.setFlatCity(city);
        flat.setFlatBed(bed);
        flat.setFlatBath(bath);
        flat.setPrice(price);
        flat.setImage(ImageUtils.compressImage(image));
        return flatRepository.save(flat);
    }

    @Override
    public List<FlatResponseDto> getLikedFlats(String username) {
        // Assuming you have a method in your repository to get flats liked by a user
        List<Flat> likedFlats = flatRepository.findLikedFlatsByUsername(username);

        return likedFlats.stream()
                .map(flat -> new FlatResponseDto(flat.getId(), flat.getFlatName(), flat.getFlatCity(),
                        flat.getFlatBed(), flat.getFlatBath(), flat.getPrice(), ServletUriComponentsBuilder
                        .fromCurrentContextPath().build().toUriString() + "/api/v1/images/" + flat.getImage().getId()))
                .collect(Collectors.toList());
    }

}
