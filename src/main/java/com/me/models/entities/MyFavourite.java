package com.me.models.entities;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Table(name = "favourites")
public class MyFavourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String song;

    @Column(nullable = false)
    private String user_name;
}
