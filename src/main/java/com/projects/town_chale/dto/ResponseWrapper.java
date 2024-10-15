package com.projects.town_chale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper {

    private Boolean error;
    private String message;

}
