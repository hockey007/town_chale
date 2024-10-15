package com.projects.town_chale.model.user;

import com.projects.town_chale.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel {

    private String name;
    private String mobile;
    private String email;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

}
