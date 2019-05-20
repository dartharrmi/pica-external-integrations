package com.archilabs.pica.restwrapper.hilton.booking.service;

import com.archilabs.pica.restwrapper.hilton.booking.model.BookingRequest;
import com.archilabs.pica.restwrapper.hilton.booking.soap.HiltonBookingClient;
import com.hilton.services.types.BookRoomElement;
import com.hilton.services.types.RoomReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Component
public class HiltonBookingService implements IHiltonBookingService {

    private final HiltonBookingClient hiltonBookingClient;

    public HiltonBookingService(HiltonBookingClient hiltonBookingClient) {
        this.hiltonBookingClient = hiltonBookingClient;
    }

    @Override
    public boolean bookRoom(BookingRequest bookingRequest) throws DatatypeConfigurationException {
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setCheckin(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(bookingRequest.checkin.atStartOfDay(ZoneId.systemDefault()))));
        roomReservation.setCheckout(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(bookingRequest.checkout.atStartOfDay(ZoneId.systemDefault()))));
        roomReservation.setGuestName(bookingRequest.guestName);
        roomReservation.setHotel(bookingRequest.hotel);
        roomReservation.setRoomNumber(bookingRequest.roomNumber);

        BookRoomElement request = new BookRoomElement();
        request.setR(roomReservation);

        return hiltonBookingClient.bookHiltonRoom(request).getResult() == 1;
    }
}
