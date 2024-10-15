package com.projects.town_chale.service;

import com.projects.town_chale.exception.AuthenticationException;
import com.projects.town_chale.model.user.User;
import com.projects.town_chale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()) {
            throw new AuthenticationException("Email already in use", HttpStatus.CONFLICT);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void emailLogin(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new AuthenticationException("User not found", HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

}
