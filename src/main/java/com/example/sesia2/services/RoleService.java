package com.example.sesia2.services;

import com.example.sesia2.entities.Role;

import java.util.UUID;

public interface RoleService {
    Role save(String name);
    Role findByName(String name);
}