package com.example.retourexperience.service;

import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRest createUser(UserDetailsRequestDtoModel userDetails);

    UserRest updateUser(String userId, UpdateUserDetailsRequestDtoModel updateUser);

    void deleteUser(String userId);

    Optional<UserRest> getUser(String userId);

    List<UserRest> getUser();

}