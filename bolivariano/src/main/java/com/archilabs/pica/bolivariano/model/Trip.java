package com.archilabs.pica.bolivariano.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "Class that holds all of the details for booking a trip in Bolivariano")
public class Trip {

    @ApiModelProperty(notes = "Number of the trip", example = "21", required = true, position = 1)
    private String tripNumber;

    @ApiModelProperty(notes = "Departure city", example = "Bogotá", required = true, allowableValues = "Barranquilla, Bogotá, Cali, Cartagena, Medellin", position = 2)
    private String departureCity;

    @ApiModelProperty(notes = "Destination city", example = "Bogotá", required = true, allowableValues = "Barranquilla, Bogotá, Cali, Cartagena, Medellin", position = 3)
    private String arrivalCity;

    @ApiModelProperty(notes = "Departure time in format HHMM and in military time", example = "2350", required = true, position = 4)
    private String departureTime;

    @ApiModelProperty(notes = "Seat number", example = "2", required = true, position = 5, allowableValues = "range[1, 20]")
    private String seatNumber;

    public Trip() {
    }

    public Trip(String tripNumber, String departureCity, String arrivalCity, String departureTime, String seatNumber) {
        this.tripNumber = tripNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.seatNumber = seatNumber;
    }

    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
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
        Trip trip = (Trip) o;
        return Objects.equals(tripNumber, trip.tripNumber) &&
                Objects.equals(departureCity, trip.departureCity) &&
                Objects.equals(arrivalCity, trip.arrivalCity) &&
                Objects.equals(departureTime, trip.departureTime) &&
                Objects.equals(seatNumber, trip.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripNumber, departureCity, arrivalCity, departureTime, seatNumber);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripNumber='" + tripNumber + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
}
