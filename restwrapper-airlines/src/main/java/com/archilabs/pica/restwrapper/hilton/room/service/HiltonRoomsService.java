package com.archilabs.pica.restwrapper.hilton.room.service;

import com.archilabs.pica.restwrapper.hilton.room.model.GetRoomsRequest;
import com.archilabs.pica.restwrapper.hilton.room.soap.HiltonRoomsClient;
import com.oracle.xmlns.hiltonroomservice.HiltonRoomServiceProcessRequest;
import com.oracle.xmlns.hiltonroomservice.HiltonRoomServiceProcessResponse;
import com.oracle.xmlns.hiltonroomservice.Room;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class HiltonRoomsService implements IHiltonRoomsService {

    private final HiltonRoomsClient hiltonRoomsClient;

    public HiltonRoomsService(HiltonRoomsClient hiltonRoomsClient) {
        this.hiltonRoomsClient = hiltonRoomsClient;
    }

    @Override
    public List<Room> getRooms(GetRoomsRequest roomsRequest) throws DatatypeConfigurationException {
        HiltonRoomServiceProcessRequest request = new HiltonRoomServiceProcessRequest();
        request.setCheckIn(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(roomsRequest.checkin.atStartOfDay(ZoneId.systemDefault()))));
        request.setCheckOut(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(roomsRequest.checkout.atStartOfDay(ZoneId.systemDefault()))));
        request.setCity(roomsRequest.city);
        request.setCountry(roomsRequest.country);
        request.setType(roomsRequest.type);
        request.setRooms(BigInteger.valueOf(roomsRequest.rooms));

        return hiltonRoomsClient.getHiltonRooms(request).getResult().getRooms();
    }
}
