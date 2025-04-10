package com.me.services;

import com.me.models.NewFavouriteRequest;
import com.me.models.UpdateFavouriteRequest;
import org.springframework.http.HttpStatus;


public class FavouriteService {

    public HttpStatus getFavouriteByUserName(String userName) {
        return HttpStatus.OK;
    }

    public HttpStatus getSharedFave(String criteria) {
        return HttpStatus.OK;
    }

    public HttpStatus createFavourite(NewFavouriteRequest request) {
        return HttpStatus.OK;
    }

    public HttpStatus updateFavourite(UpdateFavouriteRequest request) {
        return HttpStatus.OK;
    }

    public HttpStatus deleteFavouriteById(String id) {
        return HttpStatus.OK;
    }
}
