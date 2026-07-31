package com.netpoint.clinic.service;

import com.netpoint.clinic.DTO.PatientDTO;
import com.netpoint.clinic.mappers.PatientMapper;
import com.netpoint.clinic.model.Patient;
import com.netpoint.clinic.repository.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PatientService Unit Tests")
class PatientServiceTest {

    @Mock private PatientRepo patientRepo;
    @Mock private PatientMapper patientMapper;
    @InjectMocks private PatientService patientService;

    private Patient patient;
    private PatientDTO patientDTO;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.id = 1;
        patient.name = "Ali Hassan";
        patient.phone = "01012345678";
        patient.age = 34;
        patient.isActive = true;

        patientDTO = new PatientDTO();
        patientDTO.setId(1);
        patientDTO.setName("Ali Hassan");
        patientDTO.setPhone("01012345678");
        patientDTO.setAge(34);
        patientDTO.setActive(true);
    }

    @Nested
    @DisplayName("findPatientById")
    class FindById {

        @Test
        @DisplayName("returns DTO when patient exists")
        void returnsDto_whenFound() {
            when(patientRepo.findById(1)).thenReturn(Optional.of(patient));
            when(patientMapper.toDto(patient)).thenReturn(patientDTO);

            PatientDTO result = patientService.findPatientById(1);

            assertThat(result).isNotNull();
            assertThat(result.getName()).isEqualTo("Ali Hassan");
            verify(patientRepo).findById(1);
        }

        @Test
        @DisplayName("returns null when patient not found")
        void returnsNull_whenNotFound() {
            when(patientRepo.findById(99)).thenReturn(Optional.empty());

            PatientDTO result = patientService.findPatientById(99);

            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("findAllPatients")
    class FindAll {

        @Test
        @DisplayName("returns list of DTOs")
        void returnsDtoList() {
            when(patientRepo.findAll()).thenReturn(List.of(patient));
            when(patientMapper.toDto(patient)).thenReturn(patientDTO);

            List<PatientDTO> result = patientService.findAllPatients();

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getName()).isEqualTo("Ali Hassan");
        }

        @Test
        @DisplayName("returns empty list when no patients")
        void returnsEmptyList() {
            when(patientRepo.findAll()).thenReturn(List.of());

            List<PatientDTO> result = patientService.findAllPatients();

            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("savePatient")
    class Save {

        @Test
        @DisplayName("saves and returns DTO")
        void savesAndReturnsDto() {
            when(patientRepo.save(patient)).thenReturn(patient);
            when(patientMapper.toDto(patient)).thenReturn(patientDTO);

            PatientDTO result = patientService.savePatient(patient);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1);
            verify(patientRepo).save(patient);
        }
    }

    @Nested
    @DisplayName("deletePatient")
    class Delete {

        @Test
        @DisplayName("calls deleteById")
        void callsDeleteById() {
            patientService.deletePatient(1);
            verify(patientRepo).deleteById(1);
        }
    }

    @Nested
    @DisplayName("findActivePatientsCount")
    class ActiveCount {

        @Test
        @DisplayName("returns map with correct key and count")
        void returnsCorrectMap() {
            when(patientRepo.findActivePatientsCount()).thenReturn(3);

            Map<String, Integer> result = patientService.findactivePatientsCount();

            assertThat(result).containsEntry("Active Patients", 3);
        }
    }

    @Nested
    @DisplayName("findAllPatientsCount")
    class AllCount {

        @Test
        @DisplayName("returns map with correct total count")
        void returnsCorrectTotal() {
            when(patientRepo.count()).thenReturn(5L);

            Map<String, Long> result = patientService.findallPatientsCount();

            assertThat(result).containsEntry("All Patients", 5L);
        }
    }
}
