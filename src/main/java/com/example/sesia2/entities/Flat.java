package com.example.sesia2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

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

    @OneToOne(fetch = EAGER, cascade = ALL)
    private Image image;

}
