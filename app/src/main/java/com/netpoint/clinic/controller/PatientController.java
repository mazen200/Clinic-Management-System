package com.netpoint.clinic.controller;

import com.netpoint.clinic.DTO.PatientDTO;
import com.netpoint.clinic.model.Patient;
import com.netpoint.clinic.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDTO> savePatient(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable int id) {
        PatientDTO patient = patientService.findPatientById(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients() {
        return ResponseEntity.ok(patientService.findAllPatients());
    }

    @GetMapping("/getnames")
    public ResponseEntity<List<String>> getNamesByAgeGreaterThan(@RequestParam int age) {
        return ResponseEntity.ok(patientService.findPatientNamesByageGreaterThan(age));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allActivePatients")
    public ResponseEntity<Map<String, Integer>> findAllActivePatients() {
        return ResponseEntity.ok(patientService.findactivePatientsCount());
    }

    @GetMapping("/allpatients")
    public ResponseEntity<Map<String, Long>> findAllPatients() {
        return ResponseEntity.ok(patientService.findallPatientsCount());
    }
}
