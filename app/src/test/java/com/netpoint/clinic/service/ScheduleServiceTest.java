package com.netpoint.clinic.service;

import com.netpoint.clinic.dtos.ScheduleDTO;
import com.netpoint.clinic.dtos.ScheduleRequest;
import com.netpoint.clinic.enums.DayOfWeek;
import com.netpoint.clinic.mappers.ScheduleMapper;
import com.netpoint.clinic.model.Doctor;
import com.netpoint.clinic.model.DoctorSchedule;
import com.netpoint.clinic.repository.DoctorRepo;
import com.netpoint.clinic.repository.ScheduleRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ScheduleService Unit Tests")
class ScheduleServiceTest {

    @Mock private ScheduleRepo scheduleRepo;
    @Mock private DoctorRepo doctorRepo;
    @Mock private ScheduleMapper scheduleMapper;
    @InjectMocks private ScheduleService scheduleService;

    private Doctor doctor;
    private DoctorSchedule schedule;
    private ScheduleDTO scheduleDTO;
    private ScheduleRequest request;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        // use reflection workaround because id is private — or add a constructor
        // For tests we just need the object to be non-null

        schedule = new DoctorSchedule();
        schedule.setDoctor(doctor);
        schedule.setDayOfWeek(DayOfWeek.MONDAY);
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(13, 0));

        scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);
        scheduleDTO.setDayOfWeek(DayOfWeek.MONDAY);
        scheduleDTO.setStartTime(LocalTime.of(9, 0));
        scheduleDTO.setEndTime(LocalTime.of(13, 0));

        request = new ScheduleRequest();
        request.setDoctorId(1L);
        request.setDayOfWeek(DayOfWeek.MONDAY);
        request.setStartTime(LocalTime.of(9, 0));
        request.setEndTime(LocalTime.of(13, 0));
    }

    // ── getAllSchedules ─────────────────────────────────────────

    @Nested
    @DisplayName("getAllSchedules")
    class GetAll {

        @Test
        @DisplayName("returns all schedules as DTOs")
        void returnsDtoList() {
            when(scheduleRepo.findAll()).thenReturn(List.of(schedule));
            when(scheduleMapper.toDto(schedule)).thenReturn(scheduleDTO);

            List<ScheduleDTO> result = scheduleService.getAllSchedules();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
        }

        @Test
        @DisplayName("returns empty list when no schedules exist")
        void returnsEmpty() {
            when(scheduleRepo.findAll()).thenReturn(List.of());

            List<ScheduleDTO> result = scheduleService.getAllSchedules();

            assertThat(result).isEmpty();
        }
    }

    // ── getScheduleByDoctorID ───────────────────────────────────

    @Nested
    @DisplayName("getScheduleByDoctorID")
    class GetByDoctorId {

        @Test
        @DisplayName("returns schedules for existing doctor")
        void returnsSchedules_whenDoctorExists() {
            when(scheduleRepo.findByDoctorId(1L)).thenReturn(Optional.of(List.of(schedule)));
            when(scheduleMapper.toDto(schedule)).thenReturn(scheduleDTO);

            List<ScheduleDTO> result = scheduleService.getScheduleByDoctorID(1L);

            assertThat(result).hasSize(1);
        }

        @Test
        @DisplayName("throws EntityNotFoundException when doctor has no schedules")
        void throwsException_whenNotFound() {
            when(scheduleRepo.findByDoctorId(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> scheduleService.getScheduleByDoctorID(99L))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("99");
        }
    }

    // ── createSchedule ──────────────────────────────────────────

    @Nested
    @DisplayName("createSchedule")
    class Create {

        @Test
        @DisplayName("saves and returns DTO when doctor exists")
        void createsSchedule() {
            when(doctorRepo.findById(1L)).thenReturn(Optional.of(doctor));
            when(scheduleRepo.save(any(DoctorSchedule.class))).thenReturn(schedule);
            when(scheduleMapper.toDto(schedule)).thenReturn(scheduleDTO);

            ScheduleDTO result = scheduleService.createSchedule(request);

            assertThat(result).isNotNull();
            assertThat(result.getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
            verify(scheduleRepo).save(any(DoctorSchedule.class));
        }

        @Test
        @DisplayName("throws EntityNotFoundException when doctor not found")
        void throwsException_whenDoctorNotFound() {
            when(doctorRepo.findById(999L)).thenReturn(Optional.empty());
            request.setDoctorId(999L);

            assertThatThrownBy(() -> scheduleService.createSchedule(request))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("999");
        }
    }

    // ── updateSchedule ──────────────────────────────────────────

    @Nested
    @DisplayName("updateSchedule")
    class Update {

        @Test
        @DisplayName("updates existing schedule and returns updated DTO")
        void updatesSchedule() {
            when(scheduleRepo.findById(1L)).thenReturn(Optional.of(schedule));
            when(scheduleRepo.save(schedule)).thenReturn(schedule);
            when(scheduleMapper.toDto(schedule)).thenReturn(scheduleDTO);

            ScheduleDTO result = scheduleService.updateSchedule(1L, request);

            assertThat(result).isNotNull();
            verify(scheduleRepo).save(schedule);
        }

        @Test
        @DisplayName("throws EntityNotFoundException when schedule ID does not exist")
        void throwsException_whenScheduleNotFound() {
            when(scheduleRepo.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> scheduleService.updateSchedule(99L, request))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("99");
        }

        @Test
        @DisplayName("only updates provided fields (partial update)")
        void partialUpdate_onlyDayChanged() {
            ScheduleRequest partialRequest = new ScheduleRequest();
            partialRequest.setDayOfWeek(DayOfWeek.FRIDAY);
            // startTime, endTime, doctorId are null → should not be changed

            when(scheduleRepo.findById(1L)).thenReturn(Optional.of(schedule));
            when(scheduleRepo.save(schedule)).thenReturn(schedule);
            when(scheduleMapper.toDto(schedule)).thenReturn(scheduleDTO);

            scheduleService.updateSchedule(1L, partialRequest);

            assertThat(schedule.getDayOfWeek()).isEqualTo(DayOfWeek.FRIDAY);
            assertThat(schedule.getStartTime()).isEqualTo(LocalTime.of(9, 0)); // unchanged
        }
    }

    // ── deleteSchedule ──────────────────────────────────────────

    @Nested
    @DisplayName("deleteSchedule")
    class Delete {

        @Test
        @DisplayName("deletes existing schedule")
        void deletesSchedule() {
            when(scheduleRepo.existsById(1L)).thenReturn(true);

            scheduleService.deleteSchedule(1L);

            verify(scheduleRepo).deleteById(1L);
        }

        @Test
        @DisplayName("throws EntityNotFoundException when schedule not found")
        void throwsException_whenNotFound() {
            when(scheduleRepo.existsById(99L)).thenReturn(false);

            assertThatThrownBy(() -> scheduleService.deleteSchedule(99L))
                    .isInstanceOf(EntityNotFoundException.class)
                    .hasMessageContaining("99");

            verify(scheduleRepo, never()).deleteById(any());
        }
    }
}
