package com.me.validators;

import com.me.models.Favourite;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFavouriteValidator implements ConstraintValidator<ValidFavourite, Favourite> {

    @Override
    public boolean isValid(Favourite value, ConstraintValidatorContext context) {
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