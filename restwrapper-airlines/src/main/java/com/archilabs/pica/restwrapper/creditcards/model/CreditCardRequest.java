package com.archilabs.pica.restwrapper.creditcards.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing the request to be done for verifying and charging credit cards.")
public class CreditCardRequest {

    @ApiModelProperty(notes = "Type of the credit card", example = "Visa", required = true, position = 1)
    public String type;

    @ApiModelProperty(notes = "Amount to be charged to the credit card, when doing verifications it's recommended that this amount does not exceed $3500 COP", example = "Visa", required = true, position = 2)
    public Double mount;

    @ApiModelProperty(notes = "Number of the credit card", example = "4593000000000000", required = true, position = 3)
    public String number;
}
