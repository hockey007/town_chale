package com.projects.town_chale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class User extends BaseModel {

    private String name;
    private String mobile;
    private String email;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

}
