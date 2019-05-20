package com.archilabs.pica.restwrapper.airlines.model.request;

import com.archilabs.pica.restwrapper.airlines.model.Airline;
import com.archilabs.pica.restwrapper.airlines.model.FlightDTO;
import com.archilabs.pica.restwrapper.airlines.model.Passenger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing the request to be done for booking a flight in the system.")
public class BookFlightRequest {

    @ApiModelProperty(notes = "Details of the flight to be booked")
    public FlightDTO flightDTO;

    @ApiModelProperty(notes = "Details of the passenger booking the flight")
    public Passenger passenger;

    @ApiModelProperty(notes = "Details of the airline")
    public Airline airline;
}
