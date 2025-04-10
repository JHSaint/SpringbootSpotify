package com.me.controllers;

import com.me.models.NewFavouriteRequest;
import com.me.services.FavouriteService;
import com.me.models.UpdateFavouriteRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "favourites"
)
public class UserFavouritesController {

    FavouriteService favouriteService = new FavouriteService();

    @GetMapping("{username}")
    public HttpStatus getFavouriteByUserName(@PathVariable String userName) {
        return favouriteService.getFavouriteByUserName(userName);
    }

    @GetMapping("{criteria}")
    public HttpStatus getSharedFave(@PathVariable String criteria) {
        return favouriteService.getSharedFave(criteria);
    }

    @PostMapping("create")
    public HttpStatus createFavourite(@Valid @RequestBody NewFavouriteRequest request) {
        return favouriteService.createFavourite(request);
    }

    @PutMapping("update")
    public HttpStatus updateFavourite(@Valid @RequestBody UpdateFavouriteRequest request) {
        return favouriteService.updateFavourite(request);
    }

    @PostMapping("delete/{id}")
    public HttpStatus deleteFavouriteById(@PathVariable String id) {
        return favouriteService.deleteFavouriteById(id);
    }
}
