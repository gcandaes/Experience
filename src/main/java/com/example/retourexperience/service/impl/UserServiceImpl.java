package com.example.retourexperience.service.impl;

import com.example.retourexperience.exceptions.UserServiceException;
import com.example.retourexperience.mapper.UserMapper;
import com.example.retourexperience.repository.UserRepository;
import com.example.retourexperience.service.UserService;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    Utils utils;

    UserMapper userMapper;

    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    //annotation pour ne pas avoir a faire un new
    @Autowired
    public UserServiceImpl(Utils utils, UserMapper userMapper, UserRepository userRepository) {
        this.utils = utils;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserRest createUser(UserDetailsRequestDtoModel userDetails) {
        UserRest returnValue = userMapper.mapToUserEntity(userDetails);
        userRepository.save(returnValue);

        return returnValue;
    }

    @Override
    public UserRest updateUser(String userId, UpdateUserDetailsRequestDtoModel updateUserDetails) {
        UserRest userBeforeUpdate = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException("The user you want to modify doesn't exist")
        );
        UserRest userUpdated = userMapper.mapToUpdateUserEntity(updateUserDetails, userBeforeUpdate);
        userRepository.save(userUpdated);

        return userUpdated;
    }

    @Override
    public void deleteUser(String userId) {
        UserRest userBeforeUpdate = userRepository.findById(userId).orElseThrow(
                () -> new UserServiceException("The user you want to delete doesn't exist")
        );
        userRepository.delete(userBeforeUpdate);
    }

    @Override
    public Optional<UserRest> getUsers(String userId) {
        return userRepository.findById(userId);

    }

    @Override
    public UserRest getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<UserRest> getUsers() {
        return userRepository.findAll();
    }
}
