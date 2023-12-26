package com.example.sesia2;

import com.example.sesia2.dto.UserRequestDto;
import com.example.sesia2.entities.Flat;
import com.example.sesia2.entities.Role;
import com.example.sesia2.entities.User;
import com.example.sesia2.repositories.RoleRepository;
import com.example.sesia2.repositories.UserRepository;
import com.example.sesia2.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@SpringBootApplication
public class Sesia2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sesia2Application.class, args);
    }

    private final UserService userService;
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner CommandLineRunnerBean(RoleRepository roleRepository) {
        return (args) -> {
            Role role = new Role();
            role.setName("USER");
            Role role1 = new Role();
            role1.setName("ADMIN");
            roleRepository.save(role1);
            roleRepository.save(role);

            List<Flat> flats = new ArrayList<>();
            UserRequestDto user = new UserRequestDto("admin", "admin", "admin");
            userService.register(user);
            userService.addRoleToUser(userRepository.findByEmail(user.getEmail()).getId(), "ADMIN");
        };
    }
}
