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
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue
    @Column
    private UUID id;

    @Column
    private String flatName;

    @Column
    private String flatCity;

    @Column
    private int flatBed;

    @Column
    private int flatBath;

    @Column
    private int price;

    @ManyToMany(fetch = EAGER)
    private List<User> users;

}
