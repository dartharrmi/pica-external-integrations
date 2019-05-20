package com.archilabs.pica.restwrapper.creditcards.controller;

import com.archilabs.pica.restwrapper.creditcards.model.CreditCardRequest;
import com.archilabs.pica.restwrapper.creditcards.service.CreditCardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditcard")
@Api(value = "Set of endpoints for verifying and chargin credit cards.")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("charge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status of charge request", response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    public ResponseEntity<Boolean> chargeCreditCard(@RequestBody @ApiParam(value = "JSON that holds the required information for charging the credit card", required = true) CreditCardRequest creditCardRequest) {
        ResponseEntity<Boolean> response;
        try {

            boolean result = creditCardService.chargeCreditCard(creditCardRequest);
            if (result) {
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
            response = new ResponseEntity<>(false, HttpStatus.OK);
        }

        return response;
    }

    @PostMapping("verify")
    @ApiOperation(value = "Verifies the credit card by charging a small amount", produces = "application/text")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status of the verification request", response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    public ResponseEntity<Boolean> verifyCreditCard(@RequestBody @ApiParam(value = "JSON that holds the required information for verifying the credit card", required = true) CreditCardRequest creditCardRequest) {
        ResponseEntity<Boolean> response;
        try {

            boolean result = creditCardService.verifyCreditCard(creditCardRequest);
            if (result) {
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e);
            response = new ResponseEntity<>(false, HttpStatus.OK);
        }

        return response;
    }
}
