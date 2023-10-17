package com.example.retourexperience.ui.controller;

import com.example.retourexperience.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.retourexperience.ui.model.request.UserDetailsRequestModel;
import com.example.retourexperience.ui.model.response.UserRest;
import com.example.retourexperience.userservice.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")//http://localhost:8080/users
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping()
    public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "25") int limit,
                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "getUser was called page = " + page + " and limit = " + limit;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

/*        pour tester les exceptions on peut lancer un null pointer exception
        String firstName = null;
        int firstNameLenght = firstName.length();*/

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        /*if we don't use dependencies injections it would be like this.

                UserRest returnValue = new UserServiceImpl().createUser(userDetails);


         Moreover this way it's not possible to test the method because not be able to mock the UserServiceImpl because
         the business logic of createuser will be involved
         so to use dependencies injections we declare global variable UserService interface and @Autowired it
         and we also need declare the implementation of the interface @service because thanks to that it will create an instance of it
         available for others classes that needed it with the @autowired
         */

        UserRest returnValue = userService.createUser(userDetails);


        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);

        return storedUserDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
