package com.projects.town_chale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class State extends BaseModel {

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_ID")
    private Country country;

}
