package com.example.location_service.entity;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEvent implements Serializable {

    private String requestId;
    private String driverId;
    private double lat;
    private double lng;

}