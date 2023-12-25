package com.example.sesia2.controllers;

import com.example.sesia2.dto.UserRequestDto;
import com.example.sesia2.dto.UserResponseDto;
import com.example.sesia2.exceptions.UsernameExistsException;
import com.example.sesia2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utkrent")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public UUID signNewUser(@RequestBody UserRequestDto userRequestDto) throws UsernameExistsException {
        return userService.register(userRequestDto);
    }

    @PostMapping("/add-role-to-user")
    public UUID addRoleToUser(@RequestParam UUID userId,
                              @RequestParam String roleName) {
        return userService.addRoleToUser(userId, roleName);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable UUID userId){
        return userService.getUser(userId);
    }

}
