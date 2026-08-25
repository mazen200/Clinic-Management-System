package com.netpoint.clinic.exceptions;

public class PatientNotFoundException extends ResourceNotFoundException {
    PatientNotFoundException(String message) {
        super(message);
    }
}
