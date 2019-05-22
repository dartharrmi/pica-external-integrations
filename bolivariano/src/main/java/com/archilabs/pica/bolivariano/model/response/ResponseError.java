package com.archilabs.pica.bolivariano.model.response;

import java.util.Date;

public class ResponseError {

    private Date errorDate;
    private String errorMessage;
    private Integer HttpStatus;

    public ResponseError(Date errorDate, String errorMessage, Integer httpStatus) {
        this.errorDate = errorDate;
        this.errorMessage = errorMessage;
        HttpStatus = httpStatus;
    }
}
