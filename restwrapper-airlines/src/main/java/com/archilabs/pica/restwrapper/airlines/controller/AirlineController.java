package com.archilabs.pica.restwrapper.airlines.controller;

import com.archilabs.pica.restwrapper.airlines.model.Airline;
import com.archilabs.pica.restwrapper.airlines.model.FlightDTO;
import com.archilabs.pica.restwrapper.airlines.model.request.BookFlightRequest;
import com.archilabs.pica.restwrapper.airlines.service.AirlinesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.List;

import static com.archilabs.pica.restwrapper.airlines.model.Airline.AA;
import static com.archilabs.pica.restwrapper.airlines.model.Airline.AVIANCA;

@RestController
@RequestMapping("/airlines")
@Api(value = "Set of endpoints for searching and booking flights in airlines that offer TouresBalon a SOAP service, like AA or Avianca.")
public class AirlineController {

    @Autowired
    private AirlinesService airlinesService;

    @GetMapping("/search")
    @ApiOperation(value = "Gets the list of flights available", produces = "application/json;charset=UTF-8")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of available flights", response = FlightDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request, usually because any of the parameters do not comply with the expected format"),
            @ApiResponse(code = 404, message = "Empty response"),
            @ApiResponse(code = 500, message = ""),
    })
    public ResponseEntity<List<FlightDTO>> searchFlight(@RequestParam("departingCity") @ApiParam(value = "Code for the departing city", required = true, example = "BOG") String departingCity,
                                                        @RequestParam("departingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "Desired departing city in ISO", required = true, example = "2019-05-28") LocalDate departingDate,
                                                        @RequestParam("arrivingCity") @ApiParam(value = "Code for the arrival city", required = true, example = "BAQ") String arrivingCity,
                                                        @RequestParam("cabin") @ApiParam(value = "Type of cabin for the flight", example = "Economy Plus", required = true) String cabin,
                                                        @RequestParam(value = "promotionCode", required = false) @ApiParam(value = "Promotion code apply discounts and offers", example = "ABC12345AA") String promotionCode,
                                                        @RequestParam("airline") @ApiParam(value = "Code of the airline offering the flight", example = "AV", required = true, allowableValues = "AV, AA") String airline) {
        ResponseEntity<List<FlightDTO>> response;
        try {
            List<FlightDTO> flights = null;
            if (Airline.getFromCode(airline).equals(AA)) {
                flights = airlinesService.searchAaFlight(departingCity, departingDate, arrivingCity, cabin, promotionCode);
            } else if (Airline.getFromCode(airline).equals(AVIANCA)) {
                flights = airlinesService.searchAviancaFlight(departingCity, departingDate, arrivingCity, cabin, promotionCode);
            }

            if (flights != null && !flights.isEmpty()) {
                response = new ResponseEntity<>(flights, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(flights, HttpStatus.NOT_FOUND);
            }

            return response;
        } catch (DatatypeConfigurationException e) {
            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping("bookFlight")
    @ApiOperation(value = "Books a flight", produces = "application/text")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status of the booking request", response = String.class),
            @ApiResponse(code = 500, message = ""),
    })
    public ResponseEntity<Boolean> bookFlight(@RequestBody @ApiParam(value = "JSON representing the booking request", required = true) BookFlightRequest bookFlightRequest) {
        ResponseEntity<Boolean> response;
        try {
            boolean result = false;
            if (bookFlightRequest.airline.equals(AA)) {
                result = airlinesService.bookAaFlight(bookFlightRequest.flightDTO, bookFlightRequest.passenger.passengerName);
            } else if (bookFlightRequest.airline.equals(AVIANCA)) {
                result = airlinesService.bookAviancaFlight(bookFlightRequest.flightDTO, bookFlightRequest.passenger);
            }

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DatatypeConfigurationException e) {
            response = new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
