package com.projects.town_chale.model.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.town_chale.model.BaseModel;
import com.projects.town_chale.model.user.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bus extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bus_type")
    @JsonProperty("bus_type")
    private BusType busType;

    @Column(name = "reg_num")
    @JsonProperty("reg_num")
    private String regNumber;

    @Column(name = "route_num")
    @JsonProperty("route_num")
    private Integer routeNumber;

    @Column(name = "total_seats")
    @JsonProperty("total_seats")
    private Integer totalSeats;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Compartment> compartments;

    private List<String> facilities;

}
