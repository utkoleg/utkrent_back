package com.example.sesia2;

import com.example.sesia2.entities.Role;
import com.example.sesia2.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sesia2Application {

    public static void main(String[] args) {
        SpringApplication.run(Sesia2Application.class, args);
    }


    @Bean
    public CommandLineRunner CommandLineRunnerBean(RoleRepository roleRepository) {
        return (args) -> {
            Role role = new Role();
            role.setName("USER");
            Role role1 = new Role();
            role1.setName("ADMIN");
            roleRepository.save(role1);
            roleRepository.save(role);
        };
    }
}
