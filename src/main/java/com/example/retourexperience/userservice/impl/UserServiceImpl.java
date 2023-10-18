package com.example.retourexperience.userservice.impl;

import com.example.retourexperience.exceptions.UserServiceException;
import com.example.retourexperience.mapper.UserMapper;
import com.example.retourexperience.repository.UserRepository;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.requestDto.UpdateUserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import com.example.retourexperience.ui.model.responseEntity.UserRest;
import com.example.retourexperience.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    Map<String, UserRest> users;
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
        Optional<UserRest> userBeforeUpdate = userRepository.findById(userId);
        if (userBeforeUpdate.isPresent()) {
            UserRest userUpdated = userMapper.mapToUpdateUserEntity(updateUserDetails, userBeforeUpdate.get());
            userRepository.save(userUpdated);
            return userUpdated;
        } else {
            throw new UserServiceException("The user you want to modify doesn't exist");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserRest> userBeforeUpdate = userRepository.findById(userId);
        if (userBeforeUpdate.isPresent()) {
            userRepository.delete(userBeforeUpdate.get());
        } else {
            throw new UserServiceException("The user you want to delete doesn't exist");
        }
    }

    @Override
    public Optional<UserRest> getUser(String userId) {
        return userRepository.findById(userId);
       /* Optional<UserRest> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserServiceException("The user you want to get doesn't exist");
        }*/
    }

    @Override
    public List<UserRest> getUser() {
        return userRepository.findAll();
    }
}
