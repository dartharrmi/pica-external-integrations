package com.archilabs.pica.restwrapper.hilton.room.controller;

import com.archilabs.pica.restwrapper.hilton.room.model.GetRoomsRequest;
import com.archilabs.pica.restwrapper.hilton.room.service.HiltonRoomsService;
import com.oracle.xmlns.hiltonroomservice.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hilton")
@Api(value = "Set of endpoints for seaching available rooms in Hilton hotels")
public class HiltonRoomsController {

    private final HiltonRoomsService hiltonRoomsService;

    public HiltonRoomsController(HiltonRoomsService hiltonRoomsService) {
        this.hiltonRoomsService = hiltonRoomsService;
    }

    @GetMapping("rooms")
    @ApiOperation(value = "Search available rooms", produces = "application/text")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of available rooms", response = Room.class),
            @ApiResponse(code = 500, message = ""),
    })
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
