package com.archilabs.pica.restwrapper.hilton.room.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(description = "Class representing the request to be done for searching the rooms available.")
public class GetRoomsRequest {

    @ApiModelProperty(notes = "City of the hotel", example = "BOG", required = true, position = 1)
    public String city;

    @ApiModelProperty(notes = "Number of rooms to book", example = "1", required = true, position = 2)
    public int rooms;

    @ApiModelProperty(notes = "Desired checkout date in ISO format", example = "2019-05-20", required = true, position = 3)
    public LocalDate checkout;

    @ApiModelProperty(notes = "Country of the hotel", example = "CO", required = true, position = 1)
    public String country;

    @ApiModelProperty(notes = "Type of the room to book", example = "Double", required = true, position = 1)
    public String type;

    @ApiModelProperty(notes = "Desired checkin date in ISO format", example = "2019-05-20", required = true, position = 6)
    public LocalDate checkin;
}
