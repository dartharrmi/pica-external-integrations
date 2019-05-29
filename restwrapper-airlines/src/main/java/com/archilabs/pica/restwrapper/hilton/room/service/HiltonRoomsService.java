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
import java.util.ArrayList;
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

        //return hiltonRoomsClient.getHiltonRooms(request).getResult().getRooms();
        List<Room> rooms = new ArrayList<>();

        Room r1 = new Room();
        r1.setNumber(new BigInteger("123456"));
        r1.setPrice(250000);
        r1.setType("Double");

        Room r2 = new Room();
        r2.setNumber(new BigInteger("123457"));
        r2.setPrice(350000);
        r2.setType("Double Premium");

        Room r3 = new Room();
        r3.setNumber(new BigInteger("123458"));
        r3.setPrice(250000);
        r3.setType("Double");

        Room r4 = new Room();
        r4.setNumber(new BigInteger("123459"));
        r4.setPrice(260000);
        r4.setType("Double");

        Room r5 = new Room();
        r5.setNumber(new BigInteger("123460"));
        r5.setPrice(240000);
        r5.setType("Double");

        Room r6 = new Room();
        r6.setNumber(new BigInteger("123461"));
        r6.setPrice(120000);
        r6.setType("Single");

        Room r7 = new Room();
        r7.setNumber(new BigInteger("123462"));
        r7.setPrice(650000);
        r7.setType("Suite");

        Room r8 = new Room();
        r8.setNumber(new BigInteger("123463"));
        r8.setPrice(550000);
        r8.setType("Suite Economy");

        Room r9 = new Room();
        r9.setNumber(new BigInteger("123464"));
        r9.setPrice(100000);
        r9.setType("Single");

        Room r10 = new Room();
        r10.setNumber(new BigInteger("123465"));
        r10.setPrice(380000);
        r10.setType("Double Premium");


        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);
        rooms.add(r5);
        rooms.add(r6);
        rooms.add(r7);
        rooms.add(r8);
        rooms.add(r9);
        rooms.add(r10);

        return rooms;
    }
}
