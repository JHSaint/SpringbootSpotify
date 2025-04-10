package com.me.models.entities;

import com.me.models.NewFavouriteRequest;
import com.me.services.FavouriteService.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.nio.charset.Charset;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favourites")
public class MyFavourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String song;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "song_id", nullable = false)
    private String songId;

    public MyFavourite(NewFavouriteRequest request){
        this.artist = request.getArtist();
        this.song = request.getSong();
        this.userName = request.getUserName();
        this.songId = song+userName;
    }

}
