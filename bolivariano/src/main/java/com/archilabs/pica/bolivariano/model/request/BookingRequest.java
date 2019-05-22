package com.archilabs.pica.bolivariano.model.request;

import java.util.Objects;

public class BookingRequest {

    private String lastName;
    private String name;
    private String departureDate;
    private String tripNumber;
    private String seatNumber;

    public BookingRequest() {
    }

    public BookingRequest(String lastName, String name, String departureDate, String tripNumber, String seatNumber) {
        this.lastName = lastName;
        this.name = name;
        this.departureDate = departureDate;
        this.tripNumber = tripNumber;
        this.seatNumber = seatNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequest that = (BookingRequest) o;
        return Objects.equals(lastName, that.lastName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(tripNumber, that.tripNumber) &&
                Objects.equals(seatNumber, that.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, name, departureDate, tripNumber, seatNumber);
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", tripNumber='" + tripNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
}
