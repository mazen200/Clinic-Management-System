package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.AppointmentDto;
import com.netpoint.clinic.model.Appointment;
import com.netpoint.clinic.model.Doctor;
import com.netpoint.clinic.model.Patient;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-25T21:29:43+0300",
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

        appointmentDto.setDoctorId( appointmentDoctorId( appointment ) );
        appointmentDto.setPatientId( appointmentPatientId( appointment ) );
        appointmentDto.setId( appointment.getId() );
        appointmentDto.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentDto.setAppointmentTime( appointment.getAppointmentTime() );
        appointmentDto.setStatus( appointment.getStatus() );
        appointmentDto.setCreatedAt( appointment.getCreatedAt() );

        return appointmentDto;
    }

    private Long appointmentDoctorId(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        return doctor.getId();
    }

    private int appointmentPatientId(Appointment appointment) {
        Patient patient = appointment.getPatient();
        if ( patient == null ) {
            return 0;
        }
        return patient.getId();
    }
}
