package com.archilabs.pica.restwrapper.hilton.booking.soap;

import com.hilton.services.types.BookRoomElement;
import com.hilton.services.types.BookRoomResponseElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;

public class HiltonBookingClient extends WebServiceGatewaySupport {

    @Autowired
    HiltonBookingClient hiltonBookingClient;

    public BookRoomResponseElement bookHiltonRoom(@NotNull BookRoomElement request) {
        return (BookRoomResponseElement) hiltonBookingClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8089/mockHiltonBookingServiceSoapHttpp",
                        request
                );
    }
}
