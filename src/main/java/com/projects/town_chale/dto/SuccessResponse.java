package com.projects.town_chale.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessResponse<T> {
    private String message;
    private T data;
}
