package com.example.retourexperience.shared;

import com.example.retourexperience.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements  ConstraintValidator<UniqueUsername, String>  {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !(userRepository.findByUserName(username) != null);
    }
}
