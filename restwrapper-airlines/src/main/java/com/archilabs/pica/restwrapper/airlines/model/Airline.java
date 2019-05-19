package com.archilabs.pica.restwrapper.airlines.model;


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
