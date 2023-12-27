package com.example.sesia2.controllers;

import com.example.sesia2.dto.FlatResponseDto;
import com.example.sesia2.dto.UserRequestDto;
import com.example.sesia2.dto.UserResponseDto;
import com.example.sesia2.exceptions.EmailExistsException;
import com.example.sesia2.exceptions.UsernameExistsException;
import com.example.sesia2.services.FlatService;
import com.example.sesia2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utkrent")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final FlatService flatService;

    @PostMapping("/sign-up")
    public UUID signNewUser(@RequestBody UserRequestDto userRequestDto) throws UsernameExistsException, EmailExistsException {
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

    @Transactional
    @PostMapping("/addFlatToUser")
    public void addFlatToUser(@RequestParam UUID userId,
                              @RequestParam UUID flatId) {
        userService.addFlatToUser(userId, flatId);
        System.out.println("Flat was added");
    }

    @PostMapping("/removeFlatFromUser")
    public void removeFlatFromUser(@RequestParam UUID userId,
                                   @RequestParam UUID flatId) {
        userService.removeFlatFromUser(userId, flatId);
        System.out.println("Flat was removed");
    }

    @GetMapping("/liked-flats/{id}")
    public ResponseEntity<List<FlatResponseDto>> getLikedFlats(@PathVariable UUID id) {
        List<FlatResponseDto> likedFlats = userService.getLikedFlats(id);
        return ResponseEntity.ok(likedFlats);
    }


}
