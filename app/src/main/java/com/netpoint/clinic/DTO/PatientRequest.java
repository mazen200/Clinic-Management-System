package com.netpoint.clinic.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientRequest {

    private String name;
    private String phone;
    private String address;
    private int age;
    private String nationality;
    private String bloodType;
    private Long userId;
}
