package com.netpoint.clinic.service;

import com.netpoint.clinic.dtos.ScheduleDTO;
import com.netpoint.clinic.dtos.ScheduleRequest;
import com.netpoint.clinic.mappers.ScheduleMapper;
import com.netpoint.clinic.model.DoctorSchedule;
import com.netpoint.clinic.repository.DoctorRepo;
import com.netpoint.clinic.repository.ScheduleRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepo scheduleRepo;
    private final DoctorRepo doctorRepo;

    public List<ScheduleDTO> getScheduleByDoctorID(Long doctorId) {
        return scheduleRepo.findByDoctorId(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("No schedules found for doctor id: " + doctorId))
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepo.findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    public ScheduleDTO createSchedule(ScheduleRequest request) {
        var doctor = doctorRepo.findById(request.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + request.getDoctorId()));


        DoctorSchedule schedule = DoctorSchedule.builder()
                                .doctor(doctor)
                                .endTime(request.getEndTime())
                                .startTime(request.getStartTime())
                                .dayOfWeek(request.getDayOfWeek())
                                .build();

        return scheduleMapper.toDto(scheduleRepo.save(schedule));
    }

    public ScheduleDTO updateSchedule(Long id, ScheduleRequest request) {
        DoctorSchedule schedule = scheduleRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + id));

        if (request.getDayOfWeek() != null) {
            schedule.setDayOfWeek(request.getDayOfWeek());
        }
        if (request.getStartTime() != null) {
            schedule.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            schedule.setEndTime(request.getEndTime());
        }
        if (request.getDoctorId() != null) {
            var doctor = doctorRepo.findById(request.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + request.getDoctorId()));
            schedule.setDoctor(doctor);
        }

        return scheduleMapper.toDto(scheduleRepo.save(schedule));
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepo.existsById(id)) {
            throw new EntityNotFoundException("Schedule not found with id: " + id);
        }
        scheduleRepo.deleteById(id);
    }
}

