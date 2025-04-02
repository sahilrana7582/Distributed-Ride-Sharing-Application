package com.example.notification_service.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideAcceptEvent {

    private String rideStatus;
    private String message;

}