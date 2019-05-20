package com.archilabs.pica.restwrapper.hilton.room.controller;

import com.archilabs.pica.restwrapper.hilton.room.model.GetRoomsRequest;
import com.archilabs.pica.restwrapper.hilton.room.service.HiltonRoomsService;
import com.oracle.xmlns.hiltonroomservice.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hilton")
public class HiltonRoomsController {

    private final HiltonRoomsService hiltonRoomsService;

    public HiltonRoomsController(HiltonRoomsService hiltonRoomsService) {
        this.hiltonRoomsService = hiltonRoomsService;
    }

    @PostMapping("rooms")
    public ResponseEntity<List<Room>> book(@RequestBody GetRoomsRequest roomsRequest) {
        ResponseEntity<List<Room>> response;
        try {

            List<Room> rooms = hiltonRoomsService.bookRoom(roomsRequest);
            if (!rooms.isEmpty()) {
                response = new ResponseEntity<>(rooms, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(rooms, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
            response = new ResponseEntity<>(null, HttpStatus.OK);
        }

        return response;
    }
}
