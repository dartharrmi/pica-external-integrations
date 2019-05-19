package com.archilabs.pica.restwrapper.airlines.configuration;

import com.archilabs.pica.restwrapper.airlines.soap.AirlineClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AirlineServiceConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.aa.services.types");
        return marshaller;
    }

    @Bean
    public AirlineClient airlineClient(Jaxb2Marshaller jaxb2Marshaller) {
        AirlineClient client = new AirlineClient();
        client.setMarshaller(jaxb2Marshaller);
        client.setUnmarshaller(jaxb2Marshaller);
        return client;
    }
}
