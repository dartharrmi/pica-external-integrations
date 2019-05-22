package com.archilabs.pica.bolivariano.controller;

import com.archilabs.pica.bolivariano.model.Trip;
import com.archilabs.pica.bolivariano.model.request.BookingRequest;
import com.archilabs.pica.bolivariano.model.request.TripsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bolivariano")
public class BolivarianoController {

    @GetMapping("trips")
    public ResponseEntity<List<Trip>> getTrips(@RequestBody TripsRequest tripsRequest) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping("booking")
    public ResponseEntity<Boolean> booking(@RequestBody BookingRequest bookingRequest) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
