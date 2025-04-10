package com.me.models;

import com.me.validators.ValidFavourite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidFavourite
public class Favourite {

    private String artist;

    private String song;

    private String userName;

}
