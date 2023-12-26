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
    public Flat addFlat(@RequestParam(name = "flatName") String name,
                        @RequestParam(name = "flatCity") String city,
                        @RequestParam(name = "flatBed") int bed,
                        @RequestParam(name = "flatBath") int bath,
                        @RequestParam(name = "price") int price,
                        @RequestParam(name = "image") MultipartFile image) throws Exception{
        System.out.println("Received parameters: " + name + ", " + city + ", " + bed + ", " + bath + ", " + price + ", " + image);
        return flatService.addFlat(name, city, bed, bath, price, image);
    }
}
