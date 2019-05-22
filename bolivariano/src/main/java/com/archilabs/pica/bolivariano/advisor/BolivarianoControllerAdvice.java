package com.archilabs.pica.bolivariano.advisor;

import com.archilabs.pica.bolivariano.controller.BolivarianoController;
import com.archilabs.pica.bolivariano.exceptions.TripsFileNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@ControllerAdvice(assignableTypes = BolivarianoController.class)
@RequestMapping(produces = "application/vnd.error+json")
public class BolivarianoControllerAdvice {

    @ExceptionHandler(TripsFileNotFoundException.class)
    public ResponseEntity<VndErrors> tripsFileNotFound(TripsFileNotFoundException exception) {
        return error(exception, HttpStatus.NOT_FOUND, String.valueOf(System.currentTimeMillis()));
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

}
