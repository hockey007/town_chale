package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Driver extends BaseModel {

    private String name;

    @Column(name = "license_num")
    @JsonProperty("license_num")
    private String licenseNumber;

    private String phone;

}
