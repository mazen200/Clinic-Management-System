package com.netpoint.clinic.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientDTO {

    private int id;
    private String name;
    private String phone;
    private String address;
    private int age;
    private String nationality;
    private String bloodType;
    private LocalDateTime createdAt;
    private boolean isActive;
}
