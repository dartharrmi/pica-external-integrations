package com.archilabs.pica.restwrapper.hilton.room.soap;

import com.oracle.xmlns.hiltonroomservice.HiltonRoomServiceProcessRequest;
import com.oracle.xmlns.hiltonroomservice.HiltonRoomServiceProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.validation.constraints.NotNull;

public class HiltonRoomsClient extends WebServiceGatewaySupport {

    @Autowired
    HiltonRoomsClient hiltonRoomsClient;

    public HiltonRoomServiceProcessResponse getHiltonRooms(@NotNull HiltonRoomServiceProcessRequest request) {
        return (HiltonRoomServiceProcessResponse) hiltonRoomsClient.getWebServiceTemplate()
                .marshalSendAndReceive(
                        "http://127.0.0.1:8089/mockHiltonBookingServiceSoapHttpp",
                        request
                );
    }
}
