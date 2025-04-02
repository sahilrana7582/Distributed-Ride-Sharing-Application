package com.example.user_service.kafka.events;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideAcceptEvent implements Serializable {


    private String rideId = (UUID.randomUUID().toString());
    private String requestId;
    private String driverId;
    private double lat;
    private double lng;

    public RideAcceptEvent(String requestId, String driverId) {
        this.requestId = requestId;
        this.driverId = driverId;
    }

}