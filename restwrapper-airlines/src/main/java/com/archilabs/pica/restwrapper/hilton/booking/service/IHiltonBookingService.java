package com.archilabs.pica.restwrapper.hilton.booking.service;

import com.archilabs.pica.restwrapper.hilton.booking.model.BookingRequest;

import javax.xml.datatype.DatatypeConfigurationException;

public interface IHiltonBookingService {

    boolean bookRoom(BookingRequest bookingRequest) throws DatatypeConfigurationException;
}
