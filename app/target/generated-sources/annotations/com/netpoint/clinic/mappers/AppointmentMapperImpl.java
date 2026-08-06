package com.netpoint.clinic.mappers;

import com.netpoint.clinic.DTO.AppointmentDto;
import com.netpoint.clinic.model.Appointment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-02T20:32:03+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDto toDto(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setId( appointment.getId() );
        appointmentDto.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentDto.setAppointmentTime( appointment.getAppointmentTime() );
        appointmentDto.setStatus( appointment.getStatus() );
        appointmentDto.setCreatedAt( appointment.getCreatedAt() );

        return appointmentDto;
    }
}
