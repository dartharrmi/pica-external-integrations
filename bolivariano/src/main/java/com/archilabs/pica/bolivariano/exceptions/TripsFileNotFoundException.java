package com.archilabs.pica.bolivariano.exceptions;

public class TripsFileNotFoundException extends Exception {

    public TripsFileNotFoundException() {
    }

    public TripsFileNotFoundException(String message) {
        super(message);
    }

    public TripsFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TripsFileNotFoundException(Throwable cause) {
        super(cause);
    }
}
