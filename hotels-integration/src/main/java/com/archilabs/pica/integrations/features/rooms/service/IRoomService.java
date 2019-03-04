package com.archilabs.pica.integrations.features.rooms.service;

import com.archilabs.pica.integrations.model.Room;

import java.util.List;

public interface IRoomService {

    List<Room> findAll();

    List<Room> findRoomsByHotelId(Integer hotelId);
}
