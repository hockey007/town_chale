package com.projects.town_chale.model.city;

import com.projects.town_chale.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City extends BaseModel {

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    private String pincode;

}
