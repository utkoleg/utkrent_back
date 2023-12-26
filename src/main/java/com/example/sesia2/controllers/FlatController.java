package com.example.sesia2.controllers;

import com.example.sesia2.entities.Flat;
import com.example.sesia2.services.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utkrent/flats")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FlatController {

    private final FlatService flatService;
    @GetMapping
    public List<Flat> getFlats(){
        return flatService.getFlats();
    }

    @PostMapping("/add-flat")
    public Flat addFlat(@RequestBody Flat flat){
        return flatService.addFlat(flat);
    }


}
