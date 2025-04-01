package com.example.user_service.kafka.events;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideAcceptEvent implements Serializable {

    private String requestId;
    private String driverId;
    private LocalDateTime acceptanceTime = LocalDateTime.now();

    public RideAcceptEvent(String requestId, String driverId) {
        this.requestId = requestId;
        this.driverId = driverId;
        this.acceptanceTime = LocalDateTime.now();
    }

}