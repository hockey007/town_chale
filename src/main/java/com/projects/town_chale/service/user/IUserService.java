package com.projects.town_chale.service.user;

import com.projects.town_chale.model.User;

public interface IUserService {
    User register(User user);
    void login(String email, String password);
}
