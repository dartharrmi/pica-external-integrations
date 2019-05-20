package com.archilabs.pica.restwrapper.hilton.room.configuration;

import com.archilabs.pica.restwrapper.hilton.room.soap.HiltonRoomsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class HiltonRoomsServiceConfiguration {

    @Bean
    public Jaxb2Marshaller hiltonMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.oracle.xmlns.hiltonroomservice");
        return marshaller;
    }

    @Bean
    public HiltonRoomsClient hiltonRoomsSoapClient(Jaxb2Marshaller hiltonMarshaller) {
        HiltonRoomsClient client = new HiltonRoomsClient();
        client.setMarshaller(hiltonMarshaller);
        client.setUnmarshaller(hiltonMarshaller);
        return client;
    }
}
