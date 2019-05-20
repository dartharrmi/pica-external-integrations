package com.archilabs.pica.restwrapper.hilton.booking.controller;

import com.archilabs.pica.restwrapper.hilton.booking.model.BookingRequest;
import com.archilabs.pica.restwrapper.hilton.booking.service.HiltonBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hilton")
public class HiltonBookingController {

    private final HiltonBookingService hiltonBookingService;

    public HiltonBookingController(HiltonBookingService hiltonBookingService) {
        this.hiltonBookingService = hiltonBookingService;
    }

    @PostMapping("booking")
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
