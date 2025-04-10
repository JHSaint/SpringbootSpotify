package com.me.validators;

import com.me.models.Favourite;
import com.me.models.NewFavouriteRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFavouriteRequestValidator implements ConstraintValidator<ValidFavouriteRequest, NewFavouriteRequest> {

    @Override
    public boolean isValid(NewFavouriteRequest value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String song = value.getSong();
        String artist = value.getArtist();
        String userName = value.getUserName();


        boolean isSongValid = song != null && !song.isEmpty();
        boolean isArtistValid = artist != null && !artist.isEmpty();
        boolean isUserNameValid = userName != null && !userName.isEmpty();

        return isSongValid && isArtistValid && isUserNameValid;

    }
}