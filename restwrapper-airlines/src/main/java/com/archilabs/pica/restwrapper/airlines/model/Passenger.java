package com.archilabs.pica.restwrapper.airlines.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class that holds all of the details of the passenger booking the flight")
public class Passenger {

    @ApiModelProperty(notes = "Name of the passenger booking the flight", example = "Sansa Stark", required = true, position = 1)
    public String passengerName;

    @ApiModelProperty(notes = "Id number of the passenger booking the flight", example = "1100800400", required = true, position = 2)
    public int passengerId;
}
