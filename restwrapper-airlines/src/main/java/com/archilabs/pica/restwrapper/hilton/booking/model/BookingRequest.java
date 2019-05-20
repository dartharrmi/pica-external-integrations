package com.archilabs.pica.restwrapper.hilton.booking.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(description = "Class representing the request to be done for booking a room.")
public class BookingRequest {

    @ApiModelProperty(notes = "Name of the guest", example = "Arya Stark", required = true, position = 1)
    public String guestName;

    @ApiModelProperty(notes = "Number of the room to book", example = "101", required = true, position = 2)
    public int roomNumber;

    @ApiModelProperty(notes = "Checkout date in ISO format", example = "2019-05-20", required = true, position = 3)
    public LocalDate checkout;

    @ApiModelProperty(notes = "Hotel", example = "Hilton 93", required = true, position = 1)
    public String hotel;

    @ApiModelProperty(notes = "Checkin date in ISO format", example = "2019-05-20", required = true, position = 5)
    public LocalDate checkin;
}
