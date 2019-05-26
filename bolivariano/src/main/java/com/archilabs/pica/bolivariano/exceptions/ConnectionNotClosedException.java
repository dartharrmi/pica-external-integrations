package com.archilabs.pica.bolivariano.exceptions;

public class ConnectionNotClosedException extends Exception {

    public ConnectionNotClosedException() {
    }

    public ConnectionNotClosedException(String message) {
        super(message);
    }

    public ConnectionNotClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionNotClosedException(Throwable cause) {
        super(cause);
    }
}
