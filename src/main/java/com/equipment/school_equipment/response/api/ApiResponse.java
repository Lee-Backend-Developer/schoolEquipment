package com.equipment.school_equipment.response.api;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ApiResponse {
    private final int statusCode;
    private final Object obj;

    public ApiResponse(HttpStatus httpStatus, Object obj) {
        this.statusCode = httpStatus.value();
        this.obj = obj;
    }
}
