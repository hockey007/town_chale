package com.projects.town_chale.model.city;

import com.projects.town_chale.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Country extends BaseModel {

    private String name;

}
