package com.netpoint.clinic.mappers;

import com.netpoint.clinic.dtos.DoctorDTO;
import com.netpoint.clinic.dtos.ScheduleDTO;
import com.netpoint.clinic.model.Doctor;
import com.netpoint.clinic.model.DoctorSchedule;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-25T21:29:43+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public ScheduleDTO toDto(DoctorSchedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId( schedule.getId() );
        scheduleDTO.setDoctor( doctorToDoctorDTO( schedule.getDoctor() ) );
        scheduleDTO.setDayOfWeek( schedule.getDayOfWeek() );
        scheduleDTO.setStartTime( schedule.getStartTime() );
        scheduleDTO.setEndTime( schedule.getEndTime() );

        return scheduleDTO;
    }

    @Override
    public DoctorSchedule toEntity(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        DoctorSchedule.DoctorScheduleBuilder doctorSchedule = DoctorSchedule.builder();

        doctorSchedule.id( scheduleDTO.getId() );
        doctorSchedule.doctor( doctorDTOToDoctor( scheduleDTO.getDoctor() ) );
        doctorSchedule.dayOfWeek( scheduleDTO.getDayOfWeek() );
        doctorSchedule.startTime( scheduleDTO.getStartTime() );
        doctorSchedule.endTime( scheduleDTO.getEndTime() );

        return doctorSchedule.build();
    }

    protected DoctorDTO doctorToDoctorDTO(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setName( doctor.getName() );
        doctorDTO.setConsultationFee( doctor.getConsultationFee() );
        doctorDTO.setActive( doctor.isActive() );
        doctorDTO.setCreatedAt( doctor.getCreatedAt() );

        return doctorDTO;
    }

    protected Doctor doctorDTOToDoctor(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setName( doctorDTO.getName() );
        doctor.setConsultationFee( doctorDTO.getConsultationFee() );
        doctor.setActive( doctorDTO.isActive() );
        doctor.setCreatedAt( doctorDTO.getCreatedAt() );

        return doctor;
    }
}
