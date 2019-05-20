package com.archilabs.pica.restwrapper.airlines.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Enum holding the different airlines.")
public enum Airline {
    AA, AVIANCA;

    public static Airline getFromCode(String code) {
        if (code.equalsIgnoreCase("AA")) {
            return AA;
        } else if (code.equalsIgnoreCase("Avianca")) {
            return AVIANCA;
        } else {
            throw new IllegalArgumentException("Invalid airline code");
        }
    }
}
