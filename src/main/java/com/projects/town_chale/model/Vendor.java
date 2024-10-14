package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vendor extends BaseModel {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "company_name")
    @JsonProperty("company_name")
    private String companyName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vendor_status")
    @JsonProperty("vendor_status")
    private VendorStatus vendorStatus;

}
