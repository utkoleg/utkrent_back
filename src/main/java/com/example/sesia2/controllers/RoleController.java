package com.example.sesia2.controllers;

import com.example.sesia2.entities.Role;
import com.example.sesia2.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utkorent/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public Role save(@RequestParam String name){
        return roleService.save(name);
    }
}
