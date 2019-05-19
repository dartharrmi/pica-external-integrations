package com.archilabs.pica.restwrapper.airlines.model.request;

import com.archilabs.pica.restwrapper.airlines.model.Airline;
import com.archilabs.pica.restwrapper.airlines.model.FlightDTO;
import com.archilabs.pica.restwrapper.airlines.model.Passenger;

public class BookFlightRequest {

    public FlightDTO flightDTO;
    public Passenger passenger;
    public Airline airline;
}
