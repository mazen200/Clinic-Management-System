package com.netpoint.clinic.controllers;

import com.netpoint.clinic.dtos.AppointmentDto;
import com.netpoint.clinic.dtos.AppointmentRequest;
import com.netpoint.clinic.dtos.AppointmentStatusRequest;
import com.netpoint.clinic.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment
            (@RequestBody AppointmentRequest request, UriComponentsBuilder uriBuilder) {

        try{
            var appointmentDto = appointmentService.createAppointment(request);
            var uri = uriBuilder.path("/appointments/{id}").buildAndExpand(appointmentDto.getId()).toUri();
            return ResponseEntity.created(uri).body(appointmentDto);
        }catch(Exception e){
            return  ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        try{
            return ResponseEntity.ok(appointmentService.getAllAppointments());
        }catch(Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(appointmentService.getAppointmentById(id));
        }catch(Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody AppointmentRequest request,@PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(appointmentService.updateAppointment(request,id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointmentStatus
            (@RequestBody AppointmentStatusRequest request, @PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(appointmentService.updateAppointmentStatus(request,id));
        }catch(Exception e){
            return   ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentDto> deleteAppointmentById(@PathVariable("id") Long id) {
        try {
            appointmentService.deleteAppointmentById(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}
