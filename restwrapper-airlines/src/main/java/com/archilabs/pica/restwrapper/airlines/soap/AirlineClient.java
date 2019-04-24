package com.archilabs.pica.restwrapper.airlines.soap;

import models.wsdl.SearchFlightElement;
import models.wsdl.SearchFlightResponseElement;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class AirlineClient extends WebServiceGatewaySupport {

    public SearchFlightResponseElement GetSearchFlightResponse() {
        SearchFlightElement request = new SearchFlightElement();

        SearchFlightResponseElement response = (SearchFlightResponseElement) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://0.0.0.0:8080/castlemock/mock/soap/project/zdWrWV/AAFlightsServiceSoapHttpMock",
                        request,
                        new SoapActionCallback("http://www.tourebalon.com/pica/integrations/airlines/GetSearchFlightResponse")
                );

        return response;
    }
}
