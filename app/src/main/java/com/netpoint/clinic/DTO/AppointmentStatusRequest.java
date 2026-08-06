package com.netpoint.clinic.DTO;

import com.netpoint.clinic.Enum.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentStatusRequest {
    AppointmentStatus status;
}
