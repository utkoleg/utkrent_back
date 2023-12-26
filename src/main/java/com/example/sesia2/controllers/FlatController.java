package com.example.sesia2.controllers;

import com.example.sesia2.dto.FlatResponseDto;
import com.example.sesia2.entities.Flat;
import com.example.sesia2.entities.Image;
import com.example.sesia2.services.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.List;

@RestController
@RequestMapping("/utkrent/flats")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FlatController {

    private final FlatService flatService;
    @GetMapping()
    public List<FlatResponseDto> getFlats(){
        return flatService.getFlats();
    }

    @PostMapping("/add-flat")
    public Flat addFlat(
            @RequestPart(name = "flatName") String name,
            @RequestPart(name = "flatCity") String city,
            @RequestPart(name = "flatBed") int bed,
            @RequestPart(name = "flatBath") int bath,
            @RequestPart(name = "price") int price,
            @RequestPart(name = "image") MultipartFile image) throws Exception {
        System.out.println("Received parameters: " + name + ", " + city + ", " + bed + ", " + bath + ", " + price + ", " + image);
        return flatService.addFlat(name, city, bed, bath, price, image);
    }
}
