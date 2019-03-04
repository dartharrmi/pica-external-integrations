package com.archilabs.pica.integrations.features.rooms.controller;

import com.archilabs.pica.integrations.model.Room;
import com.archilabs.pica.integrations.features.rooms.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    IRoomService roomService;

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{hotelId}")
    public List<Room> getRoom(@PathVariable Integer hotelId) {
        return roomService.findRoomsByHotelId(hotelId);
    }
}
