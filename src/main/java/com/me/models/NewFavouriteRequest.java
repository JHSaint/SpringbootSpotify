package com.me.models;

import com.me.validators.ValidFavouriteRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidFavouriteRequest
public class NewFavouriteRequest {

    private String artist;

    private String song;

    private String userName;

}
