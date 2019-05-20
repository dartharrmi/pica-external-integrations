package com.archilabs.pica.restwrapper.airlines.model;

import com.aa.services.types.Flight;
import com.avianca.servicios.types.Vuelo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Objects;

@ApiModel(description = "Class that holds all of the details for booking a flight")
public class FlightDTO {

    @ApiModelProperty(notes = "Type of cabin for the flight", example = "Economy Plus", required = true, position = 1)
    private String cabin;

    @ApiModelProperty(notes = "Desired date of arrival in ISO format", example = "2019-05-20", required = true, position = 2)
    private LocalDate arrivingDate;

    @ApiModelProperty(notes = "Price to be paid", example = "255000", required = true, position = 3)
    private Double price;

    @ApiModelProperty(notes = "City of arrival", example = "BOG", required = true, position = 4)
    private String arrivingCity;

    @ApiModelProperty(notes = "Some airlines offer meals during the flight, this is the field that holds that information", example = "Lunch", required = true, position = 5)
    private String meals;

    @ApiModelProperty(notes = "Desired date of departure in ISO format", example = "2019-05-20", required = true, position = 6)
    private LocalDate departingDate;

    @ApiModelProperty(notes = "City of departure", example = "BAQ", required = true, position = 7)
    private String departingCity;

    @ApiModelProperty(notes = "Unique identifier of the flight", example = "AV1234", required = true, position = 8)
    private String number;

    public static FlightDTO fromFlight(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.cabin = flight.getCabin();
        flightDTO.arrivingDate = flight.getArrivingDate().toGregorianCalendar().toZonedDateTime().toLocalDate();
        flightDTO.price = flight.getPrice();
        flightDTO.arrivingCity = flight.getArrivingCity();
        flightDTO.meals = flight.getMeals();
        if (flight.getDepartingDate() != null) {
            flightDTO.departingDate = flight.getDepartingDate().toGregorianCalendar().toZonedDateTime().toLocalDate();
        }
        if (flight.getDepartingCity() != null) {
            flightDTO.departingCity = flight.getDepartingCity();
        }
        flightDTO.number = String.valueOf(flight.getNumber());

        return flightDTO;
    }

    public static FlightDTO fromVuelo(Vuelo vuelo) {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.cabin = vuelo.getClase();
        flightDTO.arrivingDate = vuelo.getFechaLlegada().toGregorianCalendar().toZonedDateTime().toLocalDate();
        flightDTO.price = vuelo.getPrecio().doubleValue();
        flightDTO.arrivingCity = vuelo.getCiudadDestino();
        flightDTO.departingDate = vuelo.getFechaSalida().toGregorianCalendar().toZonedDateTime().toLocalDate();
        flightDTO.departingCity = vuelo.getCiudadOrigen();
        flightDTO.number = vuelo.getVuelo();

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

    public LocalDate getDepartingDate() {
        return departingDate;
    }

    public void setDepartingDate(LocalDate departingDate) {
        this.departingDate = departingDate;
    }

    public String getDepartingCity() {
        return departingCity;
    }

    public void setDepartingCity(String departingCity) {
        this.departingCity = departingCity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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
                Objects.equals(departingDate, tripDTO.departingDate) &&
                Objects.equals(departingCity, tripDTO.departingCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cabin, arrivingDate, price, arrivingCity, meals, departingDate, departingCity, number);
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "cabin='" + cabin + '\'' +
                ", arrivingDate=" + arrivingDate +
                ", price=" + price +
                ", arrivingCity='" + arrivingCity + '\'' +
                ", meals='" + meals + '\'' +
                ", departingDate=" + departingDate +
                ", departingCity='" + departingCity + '\'' +
                ", number=" + number +
                '}';
    }
}
