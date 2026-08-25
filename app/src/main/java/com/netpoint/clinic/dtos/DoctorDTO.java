package com.netpoint.clinic.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class DoctorDTO {

    private String name;
    private BigDecimal consultationFee;
    private boolean isActive;
    private LocalDateTime createdAt;
    private String specializationName;
}
