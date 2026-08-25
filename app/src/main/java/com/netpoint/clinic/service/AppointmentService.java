package com.netpoint.clinic.service;

import com.netpoint.clinic.dtos.AppointmentDto;
import com.netpoint.clinic.dtos.AppointmentRequest;
import com.netpoint.clinic.dtos.AppointmentStatusRequest;
import com.netpoint.clinic.enums.AppointmentStatus;
import com.netpoint.clinic.mappers.AppointmentMapper;
import com.netpoint.clinic.model.Appointment;
import com.netpoint.clinic.repository.AppointmentRepo;
import com.netpoint.clinic.repository.DoctorRepo;
import com.netpoint.clinic.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final AppointmentMapper appointmentMapper;

    public AppointmentDto createAppointment(AppointmentRequest request) {

        var appointment = appointmentRepo.save(toEntity(request,null));

        return appointmentMapper.toDto(appointment);
    }

    public List<AppointmentDto> getAllAppointments() {
        var appointmentList = appointmentRepo.findAll();
        if(appointmentList.isEmpty()) {
            throw new RuntimeException("No appointments found");
        }
        return appointmentList.stream()
                .map(appointmentMapper::toDto)
                .toList();

    }

    public AppointmentDto getAppointmentById(Long id) {
        var appointment = appointmentRepo.findById(id).orElseThrow(() -> new RuntimeException("No Appointment found"));
        return appointmentMapper.toDto(appointment);
    }

    public AppointmentDto updateAppointment(AppointmentRequest request, Long id) {

        var appointment  = toEntity(request,id);
        return  appointmentMapper.toDto(appointmentRepo.save(appointment));
    }

    public void deleteAppointmentById(Long id) {
        var appointment = appointmentRepo.findById(id).
                orElseThrow(() -> new RuntimeException("No Appointment found"));

        appointmentRepo.delete(appointment);
    }

    public AppointmentDto updateAppointmentStatus(AppointmentStatusRequest request, Long id) {
        var appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Appointment found"));
        appointment.setStatus(request.getStatus());
        return appointmentMapper.toDto(appointmentRepo.save(appointment));
    }

    private Appointment toEntity(AppointmentRequest appointmentRequest,Long id) {

        var doctor = doctorRepo.findById(appointmentRequest.getDoctorId())
                .orElseThrow(()-> new RuntimeException("Doctor not found"));

        var patient = patientRepo.findById(appointmentRequest.getPatientId())
                .orElseThrow(()->new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        if(id!=null) {
            appointment = appointmentRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("No Appointment found"));
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setCreatedAt(LocalDate.now());

        return appointment;
    }
}
