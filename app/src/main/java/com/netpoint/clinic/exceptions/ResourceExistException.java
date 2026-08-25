package com.netpoint.clinic.exceptions;

public class ResourceExistException extends RuntimeException {
    ResourceExistException(String message) {
        super(message);
    }
}
