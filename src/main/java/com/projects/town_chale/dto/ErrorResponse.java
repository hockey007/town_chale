package com.projects.town_chale.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private Boolean error;
    private String message;
    private String details;
}
