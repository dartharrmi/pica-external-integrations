package com.archilabs.pica.integrations.features.rooms.repository;

import com.archilabs.pica.integrations.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Room.RoomPK> {

    /*@Query(value = "SELECT * FROM ROOM r WHERE r.HOTEL_ID = hotelId", nativeQuery = true)
    List<Room> findRoomsByHotelId(@Param("hotelId") Integer hotelId);*/

    List<Room> findRoomsByHotelIdEquals(@Param("hotelId") Integer hotelId);
}
