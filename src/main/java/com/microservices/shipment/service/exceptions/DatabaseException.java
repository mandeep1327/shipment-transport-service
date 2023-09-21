package com.microservices.shipment.service.exceptions;

public class DatabaseException extends RuntimeException {

    private final String message;

    public DatabaseException(String id) {
        this.message = "Unable to perform database operation for the key " + id;
    }

    public DatabaseException(String message, String id) {
        this.message = message + " " + id;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
