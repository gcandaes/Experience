package com.example.retourexperience.shared;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface CheckDates {
    String message() default "La date de fin de mission doit être ultérieure à la date de début";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
