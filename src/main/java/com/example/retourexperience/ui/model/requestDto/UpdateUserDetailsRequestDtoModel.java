package com.example.retourexperience.ui.model.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDetailsRequestDtoModel {

    @NotNull(message = "firstname can not be null")
    private String firstName;
    @NotNull(message = "lastName can not be null")
    private String lastName;

}
