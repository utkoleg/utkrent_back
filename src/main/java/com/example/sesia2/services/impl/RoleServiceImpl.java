package com.example.sesia2.services.impl;

import com.example.sesia2.entities.Role;
import com.example.sesia2.repositories.RoleRepository;
import com.example.sesia2.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}