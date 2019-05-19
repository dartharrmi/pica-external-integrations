package com.archilabs.pica.restwrapper.airlines.soap;

import com.aa.services.types.BookFligthElement;
import com.aa.services.types.BookFligthResponseElement;
import com.aa.services.types.SearchFlightElement;
import com.aa.services.types.SearchFlightResponseElement;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;

@Component
public class AirlineClient extends WebServiceGatewaySupport {

    public SearchFlightResponseElement GetSearchFlightResponse(@NotNull SearchFlightElement request) {
        SearchFlightResponseElement response = (SearchFlightResponseElement) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockAAFlightsServiceSoapHttp",
                        request
                );

        return response;
    }

    public BookFligthResponseElement GetBookFlightResponse(@NotNull BookFligthElement request) {
        BookFligthResponseElement response = (BookFligthResponseElement) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockAAFlightsServiceSoapHttp",
                        request
                );

        return response;
    }
}