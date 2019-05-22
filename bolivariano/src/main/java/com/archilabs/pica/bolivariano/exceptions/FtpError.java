package com.archilabs.pica.bolivariano.exceptions;

public class FtpError {

    private int errorcode;
    private String errormessage;

    public FtpError(int errorcode, String errormessage) {
        this.errorcode = errorcode;
        this.errormessage = errormessage;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public String getErrormessage() {
        return errormessage;
    }

    @Override
    public String toString() {
        return "FtpError{" +
                "errorcode=" + errorcode +
                ", errormessage='" + errormessage + '\'' +
                '}';
    }
}
