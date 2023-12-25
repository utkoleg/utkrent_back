package com.example.sesia2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = EAGER)
    private List<Role> roles;

    @ManyToMany(fetch = EAGER)
    private List<Flat> flats;
}