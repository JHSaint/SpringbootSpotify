package com.me.models.repositories;

import com.me.models.entities.MyFavourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.ArrayList;

public interface SpotifyFaveRepository extends JpaRepository<MyFavourite, Long> {

    @Override
    ArrayList<MyFavourite> findAll();

    ArrayList<MyFavourite> findAllByUserName(String userName);
    ArrayList<MyFavourite> findAllBySongAndArtist(String songId, String artist);
    boolean existsBySongId(String songId);
}
