package com.netpoint.clinic.service;

import com.netpoint.clinic.dtos.PatientDTO;
import com.netpoint.clinic.mappers.PatientMapper;
import com.netpoint.clinic.model.Patient;
import com.netpoint.clinic.repository.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class PatientService {

    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;

    public PatientDTO savePatient(Patient patient) {
        return patientMapper.toDto(patientRepo.save(patient));
    }

    public PatientDTO findPatientById(int id) {
        return patientRepo.findById(id)
                .map(patientMapper::toDto)
                .orElse(null);
    }

    public List<PatientDTO> findAllPatients() {
        return patientRepo.findAll()
                .stream()
                .map(patientMapper::toDto)
                .toList();
    }

    public List<String> findPatientNamesByageGreaterThan(int age) {
        return patientRepo.findPatientNamesByageGreaterThan(age);
    }

    public void deletePatient(int id) {
        patientRepo.deleteById(id);
    }

    public Map<String, Integer> findactivePatientsCount() {
        int allActive = patientRepo.findActivePatientsCount();
        return Map.of("Active Patients", allActive);
    }

    public Map<String, Long> findallPatientsCount() {
        long allPatients = patientRepo.count();
        return Map.of("All Patients", allPatients);
    }
}














