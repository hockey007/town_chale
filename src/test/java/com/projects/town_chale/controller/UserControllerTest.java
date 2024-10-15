package com.projects.town_chale.controller;

import com.projects.town_chale.exception.AuthenticationException;
import com.projects.town_chale.model.user.User;
import com.projects.town_chale.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.projects.town_chale.TestConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    void testRegistrationSuccessful() throws Exception {
        Mockito.doNothing().when(userService).register(any(User.class));
        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.message").value(USER_REGISTERED_SUCCESSFULLY));
    }

    @Test
    void testEmailAlreadyInUse() throws Exception {
        Mockito.doThrow(new AuthenticationException(EMAIL_ALREADY_IN_USE, HttpStatus.CONFLICT))
                .when(userService).register(any(User.class));

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value(EMAIL_ALREADY_IN_USE));
    }

    @Test
    void testLoginSuccess() throws Exception {
        Mockito.doNothing().when(userService).emailLogin(anyString(), anyString());

        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.message").value(USER_LOGIN_SUCCESSFUL));
    }

    @Test
    void testUserNotFoundWithEmail() throws Exception {
        Mockito.doThrow(new AuthenticationException(USER_NOT_FOUND, HttpStatus.NOT_FOUND))
                .when(userService).emailLogin(anyString(), anyString());

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value(USER_NOT_FOUND));
    }

    @Test
    void testInvalidCredentials() throws Exception {
        Mockito.doThrow(new AuthenticationException(INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED))
                .when(userService).emailLogin(anyString(), anyString());

        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.message").value(INVALID_CREDENTIALS));
    }

}