package com.example.sesia2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;


@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    protected UUID id;

    @Column
    private String name;

    @ManyToMany(fetch = EAGER)
    private List<User> users;
}