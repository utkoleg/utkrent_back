package com.example.sesia2.services.impl;

import com.example.sesia2.dto.UserRequestDto;
import com.example.sesia2.dto.UserResponseDto;
import com.example.sesia2.entities.Flat;
import com.example.sesia2.entities.Role;
import com.example.sesia2.entities.User;
import com.example.sesia2.exceptions.EmailExistsException;
import com.example.sesia2.exceptions.UsernameExistsException;
import com.example.sesia2.repositories.UserRepository;
import com.example.sesia2.services.FlatService;
import com.example.sesia2.services.RoleService;
import com.example.sesia2.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final FlatService flatService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the database {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public UserResponseDto getUser(UUID id) {
        log.info("Fetching user with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return UserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UUID register(UserRequestDto userRequestDto) throws UsernameExistsException, EmailExistsException {
        if (usernameExist(userRequestDto.getUsername())) {
            throw new UsernameExistsException(userRequestDto.getUsername());
        }
        if (emailExist(userRequestDto.getEmail())) {
            throw new EmailExistsException(userRequestDto.getEmail());
        }
        else {
            User user = new User();
            user.setUsername(userRequestDto.getUsername());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            Role role = roleService.findByName("USER");
            user.setRoles(List.of(role));
            return userRepository.save(user).getId();
        }
    }

    @Override
    public UUID addRoleToUser(UUID userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        Role role = roleService.findByName(roleName);
        user.getRoles().add(role);
        return userRepository.save(user).getId();
    }

    @Override
    public void addFlatToUser(String username, UUID flatId) {
        User user = userRepository.findByUsername(username);
        Flat flat = flatService.getFlatById(flatId);

        if (user != null && flat != null) {
            // Check if the flat is not already in the user's list
            if (!user.getFlats().contains(flat)) {
                user.getFlats().add(flat);
                userRepository.save(user);
                log.info("Flat added to user: {}", user.getUsername());
            } else {
                log.info("Flat is already in user's list: {}", user.getUsername());
                // Handle the case when the flat is already in the user's list
                // You can throw an exception, return a specific response, or take other actions.
            }
        } else {
            log.error("User or flat not found");
        }
    }

    @Override
    public void removeFlatFromUser(String username, UUID flatId) {
        User user = userRepository.findByUsername(username);
        Flat flat = flatService.getFlatById(flatId);

        if (user != null && flat != null) {
            // Check if the flat is in the user's list
            if (user.getFlats().contains(flat)) {
                user.getFlats().remove(flat);
                userRepository.save(user);
                log.info("Flat removed from user: {}", user.getUsername());
            } else {
                log.info("Flat is not in user's list: {}", user.getUsername());
                // Handle the case when the flat is not in the user's list
                // You can throw an exception, return a specific response, or take other actions.
            }
        } else {
            log.error("User or flat not found");
        }
    }

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
