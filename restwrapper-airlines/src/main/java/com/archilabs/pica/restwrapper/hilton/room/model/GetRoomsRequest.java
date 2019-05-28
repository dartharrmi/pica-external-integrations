package com.archilabs.pica.restwrapper.hilton.room.model;

import java.time.LocalDate;

public class GetRoomsRequest {

    public String city;
    public int rooms;
    public LocalDate checkout;
    public String country;
    public String type;
    public LocalDate checkin;

    public GetRoomsRequest(String city, int rooms, LocalDate checkout, String country, String type, LocalDate checkin) {
        this.city = city;
        this.rooms = rooms;
        this.checkout = checkout;
        this.country = country;
        this.type = type;
        this.checkin = checkin;
    }
}
