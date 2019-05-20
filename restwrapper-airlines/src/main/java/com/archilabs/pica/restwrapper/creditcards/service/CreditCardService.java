package com.archilabs.pica.restwrapper.creditcards.service;

import com.archilabs.pica.restwrapper.creditcards.model.CreditCardRequest;
import com.archilabs.pica.restwrapper.creditcards.soap.CreditCardClient;
import com.creditverifier.services.ChargeCreditCardElement;
import com.creditverifier.services.VerifyCreditCardElement;
import com.creditverifier.services.types.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService implements ICreditCardService {

    private final CreditCardClient creditCardClient;

    public CreditCardService(CreditCardClient creditCardClient) {
        this.creditCardClient = creditCardClient;
    }

    @Override
    public Boolean chargeCreditCard(CreditCardRequest creditCardRequest) {
        CreditCard creditCard = new CreditCard();
        creditCard.setMount(creditCardRequest.mount);
        creditCard.setNumber(creditCardRequest.number);
        creditCard.setType(creditCardRequest.type);

        ChargeCreditCardElement request = new ChargeCreditCardElement();
        request.setCc(creditCard);

        return creditCardClient.chargeCreditCard(request).isResult();
    }

    @Override
    public Boolean verifyCreditCard(CreditCardRequest creditCardRequest) {
        CreditCard creditCard = new CreditCard();
        creditCard.setMount(creditCardRequest.mount);
        creditCard.setNumber(creditCardRequest.number);
        creditCard.setType(creditCardRequest.type);

        VerifyCreditCardElement request = new VerifyCreditCardElement();
        request.setCc(creditCard);

        return creditCardClient.verifyCreditCard(request).isResult();
    }
}
