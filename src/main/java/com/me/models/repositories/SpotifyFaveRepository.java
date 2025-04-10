package com.me.models.repositories;

import com.me.models.entities.MyFavourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface SpotifyFaveRepository extends JpaRepository<MyFavourite, Long> {

    ArrayList<MyFavourite> findByUserName(String userName);
    ArrayList<String> findBySong(String song);
}
