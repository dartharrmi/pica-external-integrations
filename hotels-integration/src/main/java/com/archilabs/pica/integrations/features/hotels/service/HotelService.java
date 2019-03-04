package com.archilabs.pica.integrations.features.hotels.service;

import com.archilabs.pica.integrations.features.reservations.touresbalon.repository.TouresBalonReservationRepository;
import com.archilabs.pica.integrations.model.Hotel;
import com.archilabs.pica.integrations.model.Room;
import com.archilabs.pica.integrations.features.hotels.repository.HotelRepository;
import com.archilabs.pica.integrations.features.rooms.repository.RoomRepository;
import com.archilabs.pica.integrations.model.TouresBalonReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final TouresBalonReservationRepository reservationRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository, TouresBalonReservationRepository reservationRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Hotel> findAllHotels() {
        return (List<Hotel>) hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> findHotelById(Integer hotelId) {
        return hotelRepository.findById(hotelId);
    }

    @Override
    public List<Room> findRoomsByHotelId(Integer hotelId) {
        return roomRepository.findRoomsByHotelIdEquals(hotelId);
    }

    @Override
    public TouresBalonReservation createReservation(TouresBalonReservation newReservation) {
        return reservationRepository.save(newReservation);
    }
}
