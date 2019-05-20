package com.archilabs.pica.restwrapper.airlines.soap;

import com.aa.services.types.BookFligthElement;
import com.aa.services.types.BookFligthResponseElement;
import com.aa.services.types.SearchFlightElement;
import com.aa.services.types.SearchFlightResponseElement;
import com.avianca.servicios.types.ConsultarVueloElement;
import com.avianca.servicios.types.ConsultarVueloResponseElement;
import com.avianca.servicios.types.ReservarVueloElement;
import com.avianca.servicios.types.ReservarVueloResponseElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;

@Component
public class AirlineClient extends WebServiceGatewaySupport {

    @Autowired
    @Qualifier(value = "americanAirlinesSoapClient")
    AirlineClient americanAirlinesSoapClient;

    @Autowired
    @Qualifier(value = "aviancaSoapClient")
    AirlineClient aviancaSoapClient;

    //region AA Airline
    public SearchFlightResponseElement searchAaFlight(@NotNull SearchFlightElement request) {
        SearchFlightResponseElement response = (SearchFlightResponseElement) americanAirlinesSoapClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockAAFlightsServiceSoapHttp",
                        request
                );

        return response;
    }

    public BookFligthResponseElement bookAaFlight(@NotNull BookFligthElement request) {
        BookFligthResponseElement response = (BookFligthResponseElement) americanAirlinesSoapClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockAAFlightsServiceSoapHttp",
                        request
                );

        return response;
    }
    //endregion

    //region Avianca Airline
    public ConsultarVueloResponseElement searchAviancaFlight(@NotNull ConsultarVueloElement request) {
        ConsultarVueloResponseElement response = (ConsultarVueloResponseElement) aviancaSoapClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockServicioAviancaVuelosSoapHttp",
                        request
                );

        return response;
    }

    public ReservarVueloResponseElement bookAviancaFlight(@NotNull ReservarVueloElement request) {
        ReservarVueloResponseElement response = (ReservarVueloResponseElement) aviancaSoapClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8088/mockServicioAviancaVuelosSoapHttp",
                        request
                );

        return response;
    }
    //endregion
}