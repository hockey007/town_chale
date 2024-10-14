package com.projects.town_chale.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vendor extends BaseModel {

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;

    @Column(name = "company_name")
    @JsonProperty("company_name")
    private String companyName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vendor_status")
    @JsonProperty("vendor_status")
    private VendorStatus vendorStatus;

}
