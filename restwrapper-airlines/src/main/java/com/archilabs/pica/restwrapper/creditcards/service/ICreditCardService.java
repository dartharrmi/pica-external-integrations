package com.archilabs.pica.restwrapper.creditcards.service;

import com.archilabs.pica.restwrapper.creditcards.model.CreditCardRequest;

public interface ICreditCardService {

    Boolean chargeCreditCard(CreditCardRequest creditCardRequest);

    Boolean verifyCreditCard(CreditCardRequest creditCardRequest);
}
