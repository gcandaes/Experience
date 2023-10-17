package com.example.retourexperience.ui.model.request;

import jakarta.validation.constraints.NotNull;

public class UpdateUserDetailsRequestModel {

    @NotNull(message = "firstname can not be null")
    private String firstName;
    @NotNull(message = "lastName can not be null")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
