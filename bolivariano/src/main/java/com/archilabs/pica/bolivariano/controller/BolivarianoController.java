package com.archilabs.pica.bolivariano.controller;

import com.archilabs.pica.bolivariano.TripsService;
import com.archilabs.pica.bolivariano.exceptions.BookingTripException;
import com.archilabs.pica.bolivariano.exceptions.ConnectionNotClosedException;
import com.archilabs.pica.bolivariano.exceptions.TripsFileNotFoundException;
import com.archilabs.pica.bolivariano.exceptions.ftp.FtpException;
import com.archilabs.pica.bolivariano.model.Trip;
import com.archilabs.pica.bolivariano.model.request.BookingRequest;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("bolivariano")
@Api(value = "Set of endpoints for searching and booking trips in Bolivariano.")
public class BolivarianoController {

    private final TripsService tripsService;

    public BolivarianoController(TripsService tripsService) {
        this.tripsService = tripsService;

        try {
            String date = new SimpleDateFormat("ddMMYYYY").format(new Date());

            tripsService.connectToFtp("127.0.0.1", "touresbalon", "qwerty");
            tripsService.retrieveTripsForTheDay("Bolivariano_consultar_disponibles.csv", Paths.get("").toAbsolutePath().toString() + "\\viajes" + date + ".csv");
        } catch (FtpException e) {
            System.exit(-1);
        }
    }

    @GetMapping("trips")
    @ApiOperation(value = "Gets the list of trips available", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of available trips", response = Trip.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request, usually because any of the parameters do not comply with the expected format"),
            @ApiResponse(code = 404, message = "Trips not found"),
            @ApiResponse(code = 500, message = "Internal Server Error, usually thrown when happens errors establishing the communication with Bolivariano."),
    })
    public ResponseEntity<List<Trip>> getTrips(@RequestParam("departureCity") @ApiParam(value = "Departure city", example = "Bogotá", required = true, allowableValues = "Barranquilla, Bogotá, Cali, Cartagena, Medellin") String departureCity,
                                               @RequestParam("arrivalCity") @ApiParam(value = "Arrival city", example = "Bogotá", required = true, allowableValues = "Barranquilla, Bogotá, Cali, Cartagena, Medellin") String arrivalCity,
                                               @RequestParam("departureTime") @ApiParam(value = "Departure time in format HHMM and in military time", example = "2350", required = true) String departureTime)
            throws TripsFileNotFoundException, ConnectionNotClosedException {

        List<Trip> foundTrips = tripsService.getTrips(departureCity, arrivalCity, departureTime);

        if (foundTrips.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foundTrips, HttpStatus.OK);
        }
    }

    @PostMapping("booking")
    @ApiOperation(value = "Books a trip in Bolivariano", produces = "application/text;charset=UTF-8")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The booking was successfully completed", response = Boolean.class),
            @ApiResponse(code = 400, message = "Bad Request, usually because any of the parameters do not comply with the expected format"),
            @ApiResponse(code = 500, message = "Internal Server Error, usually thrown when happens errors establishing the communication with Bolivariano."),
    })
    public ResponseEntity<Boolean> booking(@RequestBody BookingRequest bookingRequest) throws FtpException, BookingTripException {
        tripsService.bookTrip(bookingRequest);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }
}
