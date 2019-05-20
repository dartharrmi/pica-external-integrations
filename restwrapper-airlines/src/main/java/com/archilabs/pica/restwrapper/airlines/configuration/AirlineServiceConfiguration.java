package com.archilabs.pica.restwrapper.airlines.configuration;

import com.archilabs.pica.restwrapper.airlines.soap.AirlineClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AirlineServiceConfiguration {

    //region AA Marshaller
    @Bean
    public Jaxb2Marshaller aaMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.aa.services.types");
        return marshaller;
    }

    @Bean
    public AirlineClient americanAirlinesSoapClient(Jaxb2Marshaller aaMarshaller) {
        AirlineClient client = new AirlineClient();
        client.setMarshaller(aaMarshaller);
        client.setUnmarshaller(aaMarshaller);
        return client;
    }
    //endregion

    //region Avianca Airline
    @Bean
    public Jaxb2Marshaller aviancaMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.avianca.servicios.types");
        return marshaller;
    }

    @Bean
    public AirlineClient aviancaSoapClient(Jaxb2Marshaller aviancaMarshaller) {
        AirlineClient client = new AirlineClient();
        client.setMarshaller(aviancaMarshaller);
        client.setUnmarshaller(aviancaMarshaller);
        return client;
    }
    //endregion
}
