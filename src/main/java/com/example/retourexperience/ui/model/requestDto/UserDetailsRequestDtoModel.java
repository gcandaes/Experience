package com.example.retourexperience.ui.model.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDtoModel {

    @NotNull(message = "firstname can not be null")
    private String firstName;
    @NotNull(message = "lastName can not be null")
    private String lastName;
    @NotNull(message = "email can not be null")
    @Email
    private String email;
    @NotNull(message = "password can not be null")
    @Size(min = 8, message = "password must be greater than 8 characters")
    private String password;
    @Size(min = 2, message = "userName must be greater than 2 characters")
    private String userName;

}
