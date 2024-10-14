package com.projects.town_chale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Compartment extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @OneToMany(mappedBy = "compartment", cascade = CascadeType.ALL)
    private List<Seat> seats;

}
