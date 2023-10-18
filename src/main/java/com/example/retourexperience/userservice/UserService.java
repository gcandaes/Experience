package com.example.retourexperience.userservice;

import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.responseEntity.UserRest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRest createUser(UserDetailsRequestDtoModel userDetails);

    UserRest updateUser(String userId, UpdateUserDetailsRequestDtoModel updateUser);

    void deleteUser(String userId);

    Optional<UserRest> getUser(String userId);

    List<UserRest> getUser();

}
