package com.archilabs.pica.restwrapper.airlines.soap;

import models.wsdl.BookFligthElement;
import models.wsdl.BookFligthResponseElement;
import models.wsdl.SearchFlightElement;
import models.wsdl.SearchFlightResponseElement;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;

@Component
public class AirlineClient extends WebServiceGatewaySupport {

    public SearchFlightResponseElement GetSearchFlightResponse(@NotNull SearchFlightElement request) {
        SearchFlightResponseElement response = (SearchFlightResponseElement) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://0.0.0.0:8080/castlemock/mock/soap/project/zdWrWV/AAFlightsServiceSoapHttpMock",
                        request
                );

        return response;
    }

    public BookFligthResponseElement GetBookFlightResponse(@NotNull BookFligthElement request) {
        BookFligthResponseElement response = (BookFligthResponseElement) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://0.0.0.0:8080/castlemock/mock/soap/project/zdWrWV/AAFlightsServiceSoapHttpMock",
                        request
                );

        return response;
    }
}