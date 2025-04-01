package com.example.user_service.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestEvent implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String userId;
    private double pickupLatitude;
    private double pickupLongitude;
    private double dropoffLatitude;
    private double dropoffLongitude;
    private LocalDateTime requestTime = LocalDateTime.now();
    private String status = "PENDING";

}
