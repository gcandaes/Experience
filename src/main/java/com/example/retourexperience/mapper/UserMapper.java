package com.example.retourexperience.mapper;

import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserMapper {

    Utils utils;

    public UserMapper() {
    }

    @Autowired
    public UserMapper(Utils utils) {
        this.utils = utils;
    }

    public UserDetailsRequestDtoModel mapToUserDto(UserRest user) {
        return new UserDetailsRequestDtoModel(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername()
        );
    }

    public UserRest mapToUserEntity(UserDetailsRequestDtoModel userDto) {


        return new UserRest(utils.generateUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                passwordEncoder().encode(userDto.getPassword()),
                userDto.getUserName(),
                new ArrayList<>(),
                true,
                true,
                true,
                "USER"
        );
    }

    public UserRest mapToUpdateUserEntity(UpdateUserDetailsRequestDtoModel updateUserDetails, UserRest userBeforeUpdateDto) {

        userBeforeUpdateDto.setFirstName(updateUserDetails.getFirstName());
        userBeforeUpdateDto.setLastName(updateUserDetails.getLastName());

        return userBeforeUpdateDto;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
