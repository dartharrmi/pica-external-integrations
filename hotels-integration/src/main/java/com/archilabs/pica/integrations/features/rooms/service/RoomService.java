package com.archilabs.pica.integrations.features.rooms.service;

import com.archilabs.pica.integrations.model.Room;
import com.archilabs.pica.integrations.features.rooms.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    @Override
    public List<Room> findRoomsByHotelId(Integer hotelId) {
        return new ArrayList<>()/*(List<Room>) roomRepository.findRoomsByHotelId(hotelId)*/;
    }
}
