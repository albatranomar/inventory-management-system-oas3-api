package com.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DErrorResponse {
    private int status;
    private String error;
    private String message;
}
