package com.archilabs.pica.bolivariano.exceptions;

public class BookingTripException extends Exception {

    public BookingTripException() {
    }

    public BookingTripException(String message) {
        super(message);
    }

    public BookingTripException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingTripException(Throwable cause) {
        super(cause);
    }
}
