package com.me.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidFavouriteRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFavouriteRequest {
    String message() default "All favourites must have a valid Song, Artist and Username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
