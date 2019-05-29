package com.archilabs.pica.restwrapper.hilton.room.service;

import com.archilabs.pica.restwrapper.hilton.room.model.GetRoomsRequest;
import com.oracle.xmlns.hiltonroomservice.Room;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

public interface IHiltonRoomsService {

    List<Room> getRooms(GetRoomsRequest roomsRequest) throws DatatypeConfigurationException;
}
