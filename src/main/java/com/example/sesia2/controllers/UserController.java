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

    @PostMapping("/addFlatToUser")
        public void addFlatToUser(@RequestParam String username,
                                  @RequestParam UUID flatId){
        userService.addFlatToUser(username, flatId);
        System.out.println("Flat was added");
    }

    @PostMapping("/removeFlatFromUser")
    public void removeFlatFromUser(@RequestParam String username,
                                   @RequestParam UUID flatId) {
        userService.removeFlatFromUser(username, flatId);
        System.out.println("Flat was removed");
    }

    @GetMapping("/liked-flats/{username}")
    public ResponseEntity<List<FlatResponseDto>> getLikedFlats(@PathVariable String username) {
        List<FlatResponseDto> likedFlats = flatService.getLikedFlats(username);
        return ResponseEntity.ok(likedFlats);
    }


}
