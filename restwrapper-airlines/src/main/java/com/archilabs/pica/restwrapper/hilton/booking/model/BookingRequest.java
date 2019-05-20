package com.archilabs.pica.restwrapper.hilton.booking.model;

import java.time.LocalDate;

public class BookingRequest {

    public String guestName;
    public int roomNumber;
    public LocalDate checkout;
    public String hotel;
    public LocalDate checkin;
}
