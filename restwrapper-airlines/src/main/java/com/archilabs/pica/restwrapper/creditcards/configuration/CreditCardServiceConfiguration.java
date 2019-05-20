package com.archilabs.pica.restwrapper.creditcards.configuration;

import com.archilabs.pica.restwrapper.airlines.soap.AirlineClient;
import com.archilabs.pica.restwrapper.creditcards.soap.CreditCardClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CreditCardServiceConfiguration {

    @Bean
    public Jaxb2Marshaller creditCardMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.creditverifier.services.types");
        return marshaller;
    }

    @Bean
    public CreditCardClient creditCardSoapClient(Jaxb2Marshaller creditCardMarshaller) {
        CreditCardClient client = new CreditCardClient();
        client.setMarshaller(creditCardMarshaller);
        client.setUnmarshaller(creditCardMarshaller);
        return client;
    }
}
