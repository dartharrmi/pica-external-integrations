package com.archilabs.pica.restwrapper.creditcards.controller;

import com.archilabs.pica.restwrapper.creditcards.model.CreditCardRequest;
import com.archilabs.pica.restwrapper.creditcards.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("charge")
    public ResponseEntity<Boolean> chargeCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
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
    public ResponseEntity<Boolean> verifyCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
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
