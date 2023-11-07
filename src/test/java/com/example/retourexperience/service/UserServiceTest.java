package com.example.retourexperience.service;

import com.example.retourexperience.mapper.UserMapper;
import com.example.retourexperience.repository.UserRepository;
import com.example.retourexperience.service.impl.UserServiceImpl;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock private UserMapper userMapper;
    @Mock
    private Utils utils;

    @InjectMocks
    private UserServiceImpl userService;



@Test
    public void CreateUserTest(){
        //Arrange
        UserDetailsRequestDtoModel userDetails = new UserDetailsRequestDtoModel("jean.bidon@gmail.com", "Password1", "jeanb");
    //UserRest userRest = new UserRest("01","Jean", "Bidon", "jean.bidon@gmail.com", "Password1", "jeanb", new ArrayList<>());

/*        when(userMapper.mapToUserEntity(any())).thenReturn(userRest);
        when(userRepository.save(userRest)).thenReturn(userRest);*/

        //Act
        UserRest result =  userService.createUser(userDetails);


        //Assert
        assertTrue(result.getEmail().equalsIgnoreCase("jean.bidon@gmail.com"));
        assertTrue(result.getPassword().equals("Password1"));
        assertTrue(result.getUsername().equalsIgnoreCase("jeanb"));

    }
}
