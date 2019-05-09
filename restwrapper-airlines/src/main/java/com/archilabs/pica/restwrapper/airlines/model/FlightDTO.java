package com.archilabs.pica.restwrapper.airlines.model;

import models.wsdl.Flight;

import java.time.LocalDate;
import java.util.Objects;

public class FlightDTO {

    private String cabin;
    private LocalDate arrivingDate;
    private Double price;
    private String arrivingCity;
    private String meals;
    private LocalDate departinDate;
    private String departinCity;
    private int number;

    public static FlightDTO fromFlight(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.cabin = flight.getCabin();
        flightDTO.arrivingDate = flight.getArrivingDate().toGregorianCalendar().toZonedDateTime().toLocalDate();
        flightDTO.price = flight.getPrice();
        flightDTO.arrivingCity = flight.getArrivingCity();
        flightDTO.meals = flight.getMeals();
        flightDTO.departinDate = flight.getDepartinDate().toGregorianCalendar().toZonedDateTime().toLocalDate();
        flightDTO.departinCity = flight.getDepartinCity();
        flightDTO.number = flight.getNumber();

        return flightDTO;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public LocalDate getArrivingDate() {
        return arrivingDate;
    }

    public void setArrivingDate(LocalDate arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getArrivingCity() {
        return arrivingCity;
    }

    public void setArrivingCity(String arrivingCity) {
        this.arrivingCity = arrivingCity;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public LocalDate getDepartinDate() {
        return departinDate;
    }

    public void setDepartinDate(LocalDate departinDate) {
        this.departinDate = departinDate;
    }

    public String getDepartinCity() {
        return departinCity;
    }

    public void setDepartinCity(String departinCity) {
        this.departinCity = departinCity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDTO tripDTO = (FlightDTO) o;
        return number == tripDTO.number &&
                Objects.equals(cabin, tripDTO.cabin) &&
                Objects.equals(arrivingDate, tripDTO.arrivingDate) &&
                Objects.equals(price, tripDTO.price) &&
                Objects.equals(arrivingCity, tripDTO.arrivingCity) &&
                Objects.equals(meals, tripDTO.meals) &&
                Objects.equals(departinDate, tripDTO.departinDate) &&
                Objects.equals(departinCity, tripDTO.departinCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cabin, arrivingDate, price, arrivingCity, meals, departinDate, departinCity, number);
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "cabin='" + cabin + '\'' +
                ", arrivingDate=" + arrivingDate +
                ", price=" + price +
                ", arrivingCity='" + arrivingCity + '\'' +
                ", meals='" + meals + '\'' +
                ", departinDate=" + departinDate +
                ", departinCity='" + departinCity + '\'' +
                ", number=" + number +
                '}';
    }
}
