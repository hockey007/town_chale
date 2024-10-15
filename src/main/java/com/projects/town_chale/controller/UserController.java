package com.projects.town_chale.controller;

import com.projects.town_chale.dto.LoginRequest;
import com.projects.town_chale.dto.ResponseWrapper;
import com.projects.town_chale.model.user.User;
import com.projects.town_chale.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper> register(@RequestBody User user) {
        userServiceImpl.register(user);

        ResponseWrapper response = new ResponseWrapper(false, "User registered successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper> login(@RequestBody LoginRequest loginRequest) {
        userServiceImpl.emailLogin(loginRequest.getEmail(), loginRequest.getPassword());

        ResponseWrapper response = new ResponseWrapper(false, "User login successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
