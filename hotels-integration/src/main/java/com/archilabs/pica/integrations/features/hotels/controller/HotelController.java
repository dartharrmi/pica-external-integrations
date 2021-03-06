package com.archilabs.pica.integrations.features.hotels.controller;

import com.archilabs.pica.integrations.features.hotels.service.IHotelService;
import com.archilabs.pica.integrations.model.Hotel;
import com.archilabs.pica.integrations.model.Room;
import com.archilabs.pica.integrations.model.TouresBalonReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private IHotelService hotelService;

    @Autowired
    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Access-Control-Allow-Origin", "http://localhost:8080");

        return new ResponseEntity<>(hotelService.findAllHotels(), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Integer hotelId) {
        ResponseEntity<Hotel> response;
        Optional<Hotel> optionalResponse = hotelService.findHotelById(hotelId);
        response = optionalResponse.map(hotel -> new ResponseEntity<>(hotel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        return response;
    }

    @GetMapping("/{hotelId}/rooms")
    public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable Integer hotelId) {
        ResponseEntity<List<Room>> response;
        List<Room> rooms = hotelService.findRoomsByHotelId(hotelId);

        if (!rooms.isEmpty()) {
            response = new ResponseEntity<>(rooms, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(rooms, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PostMapping("reservations/create")
    public ResponseEntity<TouresBalonReservation> createReservation(@RequestBody TouresBalonReservation touresBalonReservation) {
        ResponseEntity<TouresBalonReservation> response;

        try {
            TouresBalonReservation r = hotelService.createReservation(touresBalonReservation);
            response = new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity("Please verify that the hotel and room exists", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping("/{hotelId}/reservations")
    public ResponseEntity<List<TouresBalonReservation>> getReservations(@PathVariable Integer hotelId) {
        ResponseEntity<List<TouresBalonReservation>> response;

        try {
            List<TouresBalonReservation> r = hotelService.getAllReservations(hotelId);
            response = new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity("Hotel doesn't have reservations", HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
