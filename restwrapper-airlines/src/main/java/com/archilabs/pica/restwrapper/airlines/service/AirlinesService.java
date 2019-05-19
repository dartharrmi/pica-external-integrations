package com.archilabs.pica.restwrapper.airlines.service;

import com.aa.services.types.*;
import com.archilabs.pica.restwrapper.airlines.model.FlightDTO;
import com.archilabs.pica.restwrapper.airlines.soap.AirlineClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class AirlinesService implements IAirlinesService {

    @Autowired
    private AirlineClient airlineClient;

    //region AA Airlines
    public List<FlightDTO> searchAaFlight(String departingCity, LocalDate departingDate,
                                          String arrivingCity, String cabin, @Nullable String promotionCode) throws DatatypeConfigurationException {
        SearchFlightElement request = new SearchFlightElement();
        request.setDepartinCity(departingCity);
        request.setDepartinDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(departingDate.atStartOfDay(ZoneId.systemDefault()))));
        request.setArrivingCity(arrivingCity);
        request.setCabin(cabin);
        request.setPromotionCode(promotionCode);

        List<FlightDTO> flights = new ArrayList<>();
        SearchFlightResponseElement responseElement = airlineClient.searchAaFlight(request);
        for (Trip trip : responseElement.getResult()) {
            for (Flight flight : trip.getFlights()) {
                flights.add(FlightDTO.fromFlight(flight));
            }
        }

        return flights;
    }

    public Boolean bookAaFlight(FlightDTO flightDto, String passengerName) throws DatatypeConfigurationException {
        Flight flight = new Flight();
        flight.setArrivingCity(flight.getArrivingCity());
        flight.setArrivingDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(flightDto.getArrivingDate().atStartOfDay(ZoneId.systemDefault()))));
        flight.setDepartinCity(flight.getDepartinCity());
        flight.setDepartinDate(flight.getDepartinDate());
        flight.setCabin(flight.getCabin());
        flight.setMeals(flightDto.getMeals());
        flight.setNumber(flightDto.getNumber());
        flight.setPrice(flightDto.getPrice());

        BookFligthElement soapRequest = new BookFligthElement();
        soapRequest.setF(flight);
        soapRequest.setPassengerName(passengerName);

        BookFligthResponseElement responseElement = airlineClient.bookAaFlight(soapRequest);
        return responseElement.isResult();
    }
    //endregion
}
