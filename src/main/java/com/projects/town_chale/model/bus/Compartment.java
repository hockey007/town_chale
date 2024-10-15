package com.projects.town_chale.model.bus;

import com.projects.town_chale.model.BaseModel;
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
