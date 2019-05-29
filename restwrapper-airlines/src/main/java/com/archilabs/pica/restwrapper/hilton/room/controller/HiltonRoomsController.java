package com.archilabs.pica.restwrapper.hilton.room.controller;

import com.archilabs.pica.restwrapper.hilton.room.model.GetRoomsRequest;
import com.archilabs.pica.restwrapper.hilton.room.service.HiltonRoomsService;
import com.oracle.xmlns.hiltonroomservice.Room;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hilton")
public class HiltonRoomsController {

    private final HiltonRoomsService hiltonRoomsService;

    public HiltonRoomsController(HiltonRoomsService hiltonRoomsService) {
        this.hiltonRoomsService = hiltonRoomsService;
    }

    @GetMapping("rooms")
    public ResponseEntity<List<Room>> book(@RequestParam("city") String city,
                                           @RequestParam("rooms") int rooms,
                                           @RequestParam("checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
                                           @RequestParam("country") String country,
                                           @RequestParam("type") String type,
                                           @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin) {
        ResponseEntity<List<Room>> response;
        try {
            GetRoomsRequest g = new GetRoomsRequest(city, rooms, checkout, country, type, checkin);

            List<Room> roomsResponse = hiltonRoomsService.getRooms(g);
            if (!roomsResponse.isEmpty()) {
                response = new ResponseEntity<>(roomsResponse, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(roomsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
            response = new ResponseEntity<>(null, HttpStatus.OK);
        }

        return response;
    }
}
