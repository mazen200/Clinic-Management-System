package com.netpoint.clinic.dtos;

import com.netpoint.clinic.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentStatusRequest {
    AppointmentStatus status;
}
