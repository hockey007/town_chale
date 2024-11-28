package com.projects.town_chale.service.user;

import com.projects.town_chale.exception.UserAuthenticationException;
import com.projects.town_chale.model.User;
import com.projects.town_chale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent()) {
            throw new UserAuthenticationException("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new UserAuthenticationException("User not found with email: " + email);
        }

        User user = optionalUser.get();
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserAuthenticationException("Invalid credentials");
        }
    }
}
