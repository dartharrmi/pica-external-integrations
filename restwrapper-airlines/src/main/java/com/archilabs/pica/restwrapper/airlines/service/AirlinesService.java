package com.archilabs.pica.restwrapper.airlines.service;

import com.aa.services.types.*;
import com.archilabs.pica.restwrapper.airlines.model.FlightDTO;
import com.archilabs.pica.restwrapper.airlines.model.Passenger;
import com.archilabs.pica.restwrapper.airlines.soap.AirlineClient;
import com.avianca.servicios.types.*;
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

    private final AirlineClient airlineClient;

    public AirlinesService(AirlineClient airlineClient) {
        this.airlineClient = airlineClient;
    }

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
        flight.setNumber(Integer.parseInt(flightDto.getNumber()));
        flight.setPrice(flightDto.getPrice());

        BookFligthElement soapRequest = new BookFligthElement();
        soapRequest.setF(flight);
        soapRequest.setPassengerName(passengerName);

        BookFligthResponseElement responseElement = airlineClient.bookAaFlight(soapRequest);
        return responseElement.isResult();
    }
    //endregion

    //region Avianca
    public List<FlightDTO> searchAviancaFlight(String departingCity, LocalDate departingDate,
                                               String arrivingCity, String cabin, @Nullable String promotionCode) throws DatatypeConfigurationException {

        ConsultarVueloElement request = new ConsultarVueloElement();
        request.setCiudadOrigen(departingCity);
        request.setFechaSalida(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(departingDate.atStartOfDay(ZoneId.systemDefault()))));
        request.setCiudadDestino(arrivingCity);
        request.setClase(cabin);

        List<FlightDTO> flights = new ArrayList<>();
        ConsultarVueloResponseElement responseElement = airlineClient.searchAviancaFlight(request);
        for (Vuelo v : responseElement.getResult()) {
            flights.add(FlightDTO.fromVuelo(v));
        }

        return flights;
    }

    public Boolean bookAviancaFlight(FlightDTO flightDto, Passenger passenger) throws DatatypeConfigurationException {
        Vuelo v = new Vuelo();
        v.setCiudadDestino(flightDto.getArrivingCity());
        v.setFechaLlegada(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(flightDto.getArrivingDate().atStartOfDay(ZoneId.systemDefault()))));
        v.setClase(flightDto.getCabin());
        v.setFechaSalida(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(flightDto.getDepartingDate().atStartOfDay(ZoneId.systemDefault()))));
        v.setVuelo(flightDto.getNumber());
        v.setPrecio(flightDto.getPrice().longValue());

        ReservarVueloElement soapRequest = new ReservarVueloElement();
        soapRequest.setVuelo(v);
        soapRequest.setNombrePasajero(passenger.passengerName);
        soapRequest.setNumeroIdentidadPasajero(passenger.passengerId);

        ReservarVueloResponseElement responseElement = airlineClient.bookAviancaFlight(soapRequest);
        return responseElement.isResult();
    }
    //endregion
}
