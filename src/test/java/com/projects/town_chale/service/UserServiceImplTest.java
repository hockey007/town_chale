package com.projects.town_chale.service;

import com.projects.town_chale.exception.AuthenticationException;
import com.projects.town_chale.model.user.User;
import com.projects.town_chale.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.projects.town_chale.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail(TEST_EMAIl);
        user.setPassword(TEST_PASSWORD);
    }

    @Test
    void testUserRegistrationSuccessful() {
        when(userRepository.findByEmail(TEST_EMAIl)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(TEST_PASSWORD)).thenReturn("encodedPassword");

        userService.register(user);

        verify(userRepository).save(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void testUserRegistrationFailsWhenEmailAlreadyExists() {
        when(userRepository.findByEmail(TEST_EMAIl)).thenReturn(Optional.of(user));

        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> userService.register(user));

        assertEquals(EMAIL_ALREADY_IN_USE, exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        verify(userRepository, never()).save(user);
    }

    @Test
    void testLoginSuccess() {
        User existingUser = new User();
        existingUser.setEmail(TEST_EMAIl);
        existingUser.setPassword(passwordEncoder.encode(TEST_PASSWORD));

        when(userRepository.findByEmail(TEST_EMAIl)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(TEST_PASSWORD, existingUser.getPassword())).thenReturn(true);
        userService.emailLogin(TEST_EMAIl, TEST_PASSWORD);
        verify(userRepository).findByEmail(TEST_EMAIl);
    }

    @Test
    void testUserNotFoundWithEmail() {
        when(userRepository.findByEmail(TEST_EMAIl)).thenReturn(Optional.empty());

        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> userService.emailLogin(TEST_EMAIl, TEST_PASSWORD));

        assertEquals(USER_NOT_FOUND, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(userRepository).findByEmail(TEST_EMAIl);
    }

    @Test
    void testInvalidCredentials() {
        User existingUser = new User();
        existingUser.setEmail(TEST_EMAIl);
        existingUser.setPassword(passwordEncoder.encode(TEST_PASSWORD));

        when(userRepository.findByEmail(TEST_EMAIl)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(TEST_PASSWORD, existingUser.getPassword())).thenReturn(false);

        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> userService.emailLogin(TEST_EMAIl, TEST_PASSWORD));

        assertEquals(INVALID_CREDENTIALS, exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        verify(userRepository).findByEmail(TEST_EMAIl);
    }

}