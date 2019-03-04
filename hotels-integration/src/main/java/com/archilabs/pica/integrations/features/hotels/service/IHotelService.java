package com.archilabs.pica.integrations.features.hotels.service;

import com.archilabs.pica.integrations.model.Hotel;
import com.archilabs.pica.integrations.model.Room;
import com.archilabs.pica.integrations.model.TouresBalonReservation;

import java.util.List;
import java.util.Optional;

public interface IHotelService {

    List<Hotel> findAllHotels();

    Optional<Hotel> findHotelById(Integer hotelId);

    List<Room> findRoomsByHotelId(Integer hotelId);

    TouresBalonReservation createReservation(TouresBalonReservation newReservation);
}
