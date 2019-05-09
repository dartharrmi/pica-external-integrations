package com.archilabs.pica.restwrapper.airlines.controller;

import com.archilabs.pica.restwrapper.airlines.model.FlightDTO;
import com.archilabs.pica.restwrapper.airlines.model.request.BookFlightRequest;
import com.archilabs.pica.restwrapper.airlines.service.AirlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    private AirlinesService airlinesService;

    @Autowired
    public AirlineController(AirlinesService airlinesService) {
        this.airlinesService = airlinesService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> getAllFlights(@RequestParam("departingCity") String departingCity,
                                                         @RequestParam("departingDate") LocalDate departingDate,
                                                         @RequestParam("arrivingCity") String arrivingCity,
                                                         @RequestParam("cabin") String cabin,
                                                         @RequestParam("promotionCode") String promotionCode) {
        ResponseEntity<List<FlightDTO>> response;
        try {
            List<FlightDTO> flights = airlinesService.getFlights(departingCity, departingDate, arrivingCity, cabin, promotionCode);

            if (!flights.isEmpty()) {
                response = new ResponseEntity<>(flights, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return response;
        } catch (DatatypeConfigurationException e) {
            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PostMapping("bookFlight")
    public ResponseEntity<Boolean> bookFlight(@RequestBody BookFlightRequest bookFlightRequest) {
        ResponseEntity<Boolean> response;
        try {
            response = new ResponseEntity<>(airlinesService.bookFlight(bookFlightRequest.flightDTO, bookFlightRequest.passengerName), HttpStatus.OK);
        } catch (DatatypeConfigurationException e) {
            response = new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
