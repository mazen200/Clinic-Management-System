package com.netpoint.clinic.dtos;

import com.netpoint.clinic.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDto {
    private Long id;

    private LocalDate appointmentDate;

    private LocalDateTime appointmentTime;

    private AppointmentStatus status;

    private LocalDate createdAt;

    private Long doctorId;

    private int patientId;
}
