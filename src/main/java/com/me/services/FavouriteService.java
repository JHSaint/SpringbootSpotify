package com.me.services;

import com.me.models.Favourite;
import com.me.models.NewFavouriteRequest;
import com.me.models.entities.MyFavourite;
import com.me.models.repositories.SpotifyFaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FavouriteService {

    @Autowired
    private SpotifyFaveRepository spotifyFaveRepository;

    public ArrayList<MyFavourite> getFavouriteByUserName(String userName) {
        ArrayList<MyFavourite> fave = null;

        try {
            fave = spotifyFaveRepository.findAllByUserName(userName);
            log.info("Successfully fetched favourites for {}. There are {}!", userName,fave.size());
        }catch (Exception e){
            log.error("Error fetching favourites for {}. See {}", userName,e.getLocalizedMessage());
        }finally {
            return fave != null ? fave : new ArrayList<MyFavourite>();
        }
    }

    public ArrayList<MyFavourite> getAllFavourites() {
        ArrayList<MyFavourite> fave = null;

        try {
            fave = spotifyFaveRepository.findAll();
            log.info("Successfully fetched favourites");
        }catch (Exception e){
            log.error("Error fetching favourites  See {}", e.getLocalizedMessage());
        }finally {
            return fave != null ? fave : new ArrayList<MyFavourite>();
        }
    }

    public ArrayList<String> getFans(Favourite favourite) {
        ArrayList<MyFavourite> fans = null;

        try {
            fans = spotifyFaveRepository.findAllBySongAndArtist(favourite.getSong(), favourite.getArtist());

            log.info("Successfully fetched all fans of the song {} by {}. There are {}!", favourite.getSong(),favourite.getArtist(),fans.size());

        }catch (Exception e){
            log.error("Error fetching fans of {}", favourite.getSong());
        }finally {
            return fans != null ? fans.stream()
                    .map(MyFavourite::getUserName)
                    .collect(Collectors.toCollection(ArrayList<String>::new)) : new ArrayList<String>();
        }
    }

    public String calculateSongId(String song, String userName){
        return song+userName;
    }

    public HttpStatus createFavourite(NewFavouriteRequest request) {
        MyFavourite newFave = new MyFavourite(request);
        boolean exists = true;

        try {
            exists =  spotifyFaveRepository.existsBySongId(calculateSongId(newFave.getSong(), newFave.getUserName()));
            if (exists){
                log.info("Favourite already exist!!");
            }else {
                spotifyFaveRepository.save(newFave);
                log.info("Added a new favourite!");
            }

            return HttpStatus.CREATED;
        }catch (Exception e){
            log.error("Failed to create new favourite because of error: {}", e.getLocalizedMessage());
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus updateFavourite(NewFavouriteRequest request) {
        MyFavourite newFave = new MyFavourite(request);
        boolean exists = true;

        try {
            exists =  spotifyFaveRepository.existsBySongId(calculateSongId(newFave.getSong(), newFave.getUserName()));
            if (exists){
                log.info("Favourite already exist!!");
            }else {
                spotifyFaveRepository.save(newFave);
                log.info("Added a new favourite!");
            }

            return HttpStatus.CREATED;
        }catch (Exception e){
            log.error("Failed to create new favourite because of error: {}", e);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public void deleteFavouriteById(Long id) {

        MyFavourite fave = spotifyFaveRepository.findById(id).orElse(null);
        if (fave == null){
            log.info("No such favourite exist!!");
            return ;
        }
        spotifyFaveRepository.delete(fave);
        log.info("Deleted favourite!");
    }
}
