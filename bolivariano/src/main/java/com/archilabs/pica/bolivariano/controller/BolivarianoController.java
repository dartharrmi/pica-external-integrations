package com.archilabs.pica.bolivariano.controller;

import com.archilabs.pica.bolivariano.TripsService;
import com.archilabs.pica.bolivariano.exceptions.TripsFileNotFoundException;
import com.archilabs.pica.bolivariano.exceptions.ftp.FtpException;
import com.archilabs.pica.bolivariano.model.Trip;
import com.archilabs.pica.bolivariano.model.request.BookingRequest;
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
    public ResponseEntity<List<Trip>> getTrips(@RequestParam("departureCity") String departureCity,
                                               @RequestParam("arrivalCity") String arrivalCity,
                                               @RequestParam("departureTime") String departureTime) throws TripsFileNotFoundException {
        try {
            List<Trip> foundTrips = tripsService.getTrips(departureCity, arrivalCity, departureTime);
            return new ResponseEntity<>(foundTrips, HttpStatus.OK);
        } catch (TripsFileNotFoundException e) {
            throw e;
        }
    }

    @PostMapping("booking")
    public ResponseEntity<Boolean> booking(@RequestBody BookingRequest bookingRequest) throws IOException, FtpException {
        tripsService.bookTrip(bookingRequest);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
