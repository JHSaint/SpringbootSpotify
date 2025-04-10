package com.me.controllers;

import com.me.models.Favourite;
import com.me.models.NewFavouriteRequest;
import com.me.models.entities.MyFavourite;
import com.me.services.FavouriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "favourites"
)
public class UserFavouritesController {

    @Autowired
    FavouriteService favouriteService;

    //Returns a list of all the favourites a specific user has
    @GetMapping("{userName}")
    public ArrayList<MyFavourite> getFavouriteByUserName(@PathVariable String userName) {
        return favouriteService.getFavouriteByUserName(userName);
    }

    @GetMapping("all")
    public ArrayList<MyFavourite> getFavouriteByUserName() {
        return favouriteService.getAllFavourites();
    }

    //Returns a list of all users that have the same favourite (song)
    @GetMapping("fans")
    public ArrayList<String> getFans(@Valid @RequestBody Favourite request) {
        return favouriteService.getFans(request);
    }

    //Creates a new favourite. If it exists already say so
    @PostMapping("create")
    public HttpStatus createFavourite(@Valid @RequestBody NewFavouriteRequest request) {
        return favouriteService.createFavourite(request);
    }

    //Updates an existing favourite. If it does not exist already it will be added as a new favourite
    @PutMapping("update")
    public HttpStatus updateFavourite(@Valid @RequestBody NewFavouriteRequest request) {
        return favouriteService.updateFavourite(request);
    }

    //Deletes an existing favourite
    @DeleteMapping("delete/{id}")
    public void deleteFavouriteById(@PathVariable Long id) {
        favouriteService.deleteFavouriteById(id);
    }
}
