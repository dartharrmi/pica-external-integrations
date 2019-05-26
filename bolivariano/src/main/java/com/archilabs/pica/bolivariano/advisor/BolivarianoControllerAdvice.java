package com.archilabs.pica.bolivariano.advisor;

import com.archilabs.pica.bolivariano.controller.BolivarianoController;
import com.archilabs.pica.bolivariano.exceptions.BookingTripException;
import com.archilabs.pica.bolivariano.exceptions.ConnectionNotClosedException;
import com.archilabs.pica.bolivariano.exceptions.TripsFileNotFoundException;
import com.archilabs.pica.bolivariano.exceptions.ftp.FtpException;
import com.archilabs.pica.bolivariano.model.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice(assignableTypes = BolivarianoController.class)
public class BolivarianoControllerAdvice {

    @ExceptionHandler(TripsFileNotFoundException.class)
    public ResponseEntity<ResponseError> tripsFileNotFound(TripsFileNotFoundException exception) {
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(System.currentTimeMillis()));
    }

    @ExceptionHandler(ConnectionNotClosedException.class)
    public ResponseEntity<ResponseError> connectionNotClosedException(TripsFileNotFoundException exception) {
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(System.currentTimeMillis()));
    }

    @ExceptionHandler(BookingTripException.class)
    public ResponseEntity<ResponseError> bookingTripException(TripsFileNotFoundException exception) {
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(System.currentTimeMillis()));
    }

    @ExceptionHandler(FtpException.class)
    public ResponseEntity<ResponseError> ftpException(TripsFileNotFoundException exception) {
        return error(exception, HttpStatus.INTERNAL_SERVER_ERROR, String.valueOf(System.currentTimeMillis()));
    }

    private ResponseEntity<ResponseError> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(httpStatus.getReasonPhrase());

        return new ResponseEntity<>(new ResponseError(new Date(), message, httpStatus.value()), httpStatus);
    }

}
