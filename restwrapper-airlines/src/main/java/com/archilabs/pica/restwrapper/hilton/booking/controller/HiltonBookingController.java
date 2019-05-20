package com.archilabs.pica.restwrapper.hilton.booking.controller;

import com.archilabs.pica.restwrapper.hilton.booking.model.BookingRequest;
import com.archilabs.pica.restwrapper.hilton.booking.service.HiltonBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hilton")
@Api(value = "Set of endpoints for booking a room in Hilton hotels")
public class HiltonBookingController {

    private final HiltonBookingService hiltonBookingService;

    public HiltonBookingController(HiltonBookingService hiltonBookingService) {
        this.hiltonBookingService = hiltonBookingService;
    }

    @PostMapping("booking")
    @ApiOperation(value = "Books a room", produces = "application/text")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status of the booking request", response = Boolean.class),
            @ApiResponse(code = 500, message = ""),
    })
    public ResponseEntity<Boolean> book(@RequestBody BookingRequest bookingRequest) {
        ResponseEntity<Boolean> response;
        try {

            boolean result = hiltonBookingService.bookRoom(bookingRequest);
            if (result) {
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
            response = new ResponseEntity<>(false, HttpStatus.OK);
        }

        return response;
    }
}
