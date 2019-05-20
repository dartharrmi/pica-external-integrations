package com.archilabs.pica.restwrapper.hilton.booking.configuration;

import com.archilabs.pica.restwrapper.hilton.booking.soap.HiltonBookingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class HiltonBookingServiceConfiguration {

    @Bean
    public Jaxb2Marshaller hiltonMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.hilton.services.types");
        return marshaller;
    }

    @Bean
    public HiltonBookingClient americanAirlinesSoapClient(Jaxb2Marshaller hiltonMarshaller) {
        HiltonBookingClient client = new HiltonBookingClient();
        client.setMarshaller(hiltonMarshaller);
        client.setUnmarshaller(hiltonMarshaller);
        return client;
    }
}
