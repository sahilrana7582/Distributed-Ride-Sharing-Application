package com.example.user_service.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEvent implements Serializable {


    private String type;
    private String driverId;
    private double lat;
    private double lng;

}