package com.example.retourexperience.mapper;

import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.responseEntity.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
                user.getUserName()
        );
    }

    public UserRest mapToUserEntity(UserDetailsRequestDtoModel userDto) {

        return new UserRest(utils.generateUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getUserName()
        );
    }

    public UserRest mapToUpdateUserEntity(UpdateUserDetailsRequestDtoModel updateUserDetails, UserRest userBeforeUpdateDto) {

        userBeforeUpdateDto.setFirstName(updateUserDetails.getFirstName());
        userBeforeUpdateDto.setLastName(updateUserDetails.getLastName());

        return userBeforeUpdateDto;
    }
}
