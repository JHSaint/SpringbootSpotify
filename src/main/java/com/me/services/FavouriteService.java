package com.me.services;

import com.me.models.NewFavouriteRequest;
import com.me.models.UpdateFavouriteRequest;
import com.me.models.entities.MyFavourite;
import com.me.models.repositories.SpotifyFaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavouriteService {

    SpotifyFaveRepository spotifyFaveRepository;

    public ArrayList<MyFavourite> getFavouriteByUserName(String userName) {
        ArrayList<MyFavourite> fave = null;

        try {
            fave = spotifyFaveRepository.findByUserName(userName);
            log.info("Successfully fetched favourties for {}. There are {}!", userName,fave.size());
        }catch (Exception e){
            log.error("Error fetching favourties for {}", userName);
        }finally {
            return fave != null ? fave : new ArrayList<MyFavourite>();
        }
    }

    public ArrayList<String> getFans(String song) {
        ArrayList<String> fans = null;

        try {
            fans = spotifyFaveRepository.findBySong(song);
            log.info("Successfully fetched all fans of the song {}. There are {}!", song,fans.size());
        }catch (Exception e){
            log.error("Error fetching fans of {}", song);
        }finally {
            return fans != null ? fans : new ArrayList<String>();
        }
    }

    public HttpStatus createFavourite(NewFavouriteRequest request) {
        Optional<MyFavourite> fave = null;
        try {
            fave = spotifyFaveRepository.findByUserName(userName);
        }catch (Exception e){
            log.error("Error fetching favourtie for {}", userName);
        }finally {
            return fave != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public HttpStatus updateFavourite(UpdateFavouriteRequest request) {
        Optional<MyFavourite> fave = null;
        try {
            fave = spotifyFaveRepository.findByUserName(userName);
        }catch (Exception e){
            log.error("Error fetching favourtie for {}", userName);
        }finally {
            return fave != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public HttpStatus deleteFavouriteById(String id) {
        Optional<MyFavourite> fave = null;
        try {
            fave = spotifyFaveRepository.findByUserName(userName);
        }catch (Exception e){
            log.error("Error fetching favourtie for {}", userName);
        }finally {
            return fave != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
