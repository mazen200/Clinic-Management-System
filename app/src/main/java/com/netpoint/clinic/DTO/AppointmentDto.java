package com.netpoint.clinic.DTO;

import com.netpoint.clinic.Enum.AppointmentStatus;
import com.netpoint.clinic.model.Doctor;
import com.netpoint.clinic.model.Patient;
import jakarta.persistence.*;
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
