package com.archilabs.pica.restwrapper.creditcards.soap;

import com.creditverifier.services.ChargeCreditCardElement;
import com.creditverifier.services.ChargeCreditCardResponseElement;
import com.creditverifier.services.VerifyCreditCardElement;
import com.creditverifier.services.VerifyCreditCardResponseElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

@Component
public class CreditCardClient extends WebServiceGatewaySupport {

    @Autowired
    CreditCardClient creditCardClient;

    public ChargeCreditCardResponseElement chargeCreditCard(@NotNull ChargeCreditCardElement request) {

        return (ChargeCreditCardResponseElement) creditCardClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8089/mockCreditCardServiceSoapHttp",
                        request,
                        message -> {
                            System.out.println(message.getPayloadResult());
                            System.out.println(message.getPayloadSource());
                        }
                );
    }

    public VerifyCreditCardResponseElement verifyCreditCard(@NotNull VerifyCreditCardElement request) {

        return (VerifyCreditCardResponseElement) creditCardClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8089/mockCreditCardServiceSoapHttp",
                        request,
                        message -> {
                            System.out.println(message.getPayloadResult());
                            System.out.println(message.getPayloadSource());
                        }
                );
    }
}
