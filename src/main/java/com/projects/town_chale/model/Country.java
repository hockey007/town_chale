package com.projects.town_chale.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Country extends BaseModel {

    private String name;

}
