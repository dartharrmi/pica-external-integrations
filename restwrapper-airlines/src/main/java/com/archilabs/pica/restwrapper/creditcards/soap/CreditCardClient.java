package com.archilabs.pica.restwrapper.creditcards.soap;

import com.creditverifier.services.types.ChargeCreditCardElement;
import com.creditverifier.services.types.ChargeCreditCardResponseElement;
import com.creditverifier.services.types.VerifyCreditCardElement;
import com.creditverifier.services.types.VerifyCreditCardResponseElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@Component
public class CreditCardClient extends WebServiceGatewaySupport {

    @Autowired
    CreditCardClient creditCardClient;

    public ChargeCreditCardResponseElement chargeCreditCard(@NotNull ChargeCreditCardElement request) {

        return (ChargeCreditCardResponseElement) creditCardClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockCreditCardServiceSoapHttp",
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
                        "http://127.0.0.1:8088/mockCreditCardServiceSoapHttp",
                        request,
                        message -> {
                            System.out.println(message.getPayloadResult());
                            System.out.println(message.getPayloadSource());
                        }
                );
    }
}
