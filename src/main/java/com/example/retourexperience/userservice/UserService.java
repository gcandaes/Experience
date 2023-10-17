package com.example.retourexperience.userservice;

import com.example.retourexperience.ui.model.request.UserDetailsRequestModel;
import com.example.retourexperience.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
