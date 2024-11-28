package com.projects.town_chale.controller;

import com.projects.town_chale.dto.SuccessResponse;
import com.projects.town_chale.dto.UserLoginDto;
import com.projects.town_chale.model.User;
import com.projects.town_chale.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<User>> register(@RequestBody User user) {
        User savedUser = userService.register(user);
        SuccessResponse<User> response = SuccessResponse.<User>builder()
                .message("Saved user successfully")
                .data(savedUser)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody UserLoginDto loginDto) {
        userService.login(loginDto.getEmail(), loginDto.getPassword());

        SuccessResponse response = SuccessResponse.builder()
                .message("Saved user successfully")
                .build();
        return ResponseEntity.ok(response);
    }

}
