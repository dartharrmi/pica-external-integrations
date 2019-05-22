package com.archilabs.pica.bolivariano.model.request;

import java.util.Objects;

public class TripsRequest {

    private String departureCity;
    private String arrivalCity;
    private String departureTime;

    public TripsRequest(String departureCity, String arrivalCity, String departureTime) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TripsRequest that = (TripsRequest) o;
        return Objects.equals(departureCity, that.departureCity) &&
                Objects.equals(arrivalCity, that.arrivalCity) &&
                Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureCity, arrivalCity, departureTime);
    }

    @Override
    public String toString() {
        return "TripsRequest{" +
                "departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}
