package com.projects.town_chale.service;

import com.projects.town_chale.model.user.User;

public interface IUserService {
    void register(User user);
    void emailLogin(String email, String password);
}
