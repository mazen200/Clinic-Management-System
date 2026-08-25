package com.netpoint.clinic.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class ErrorDto {
    private int status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp;

}
