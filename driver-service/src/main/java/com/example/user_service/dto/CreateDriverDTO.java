package com.example.user_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDriverDTO {

    private String userId;
    private VehicleDTO vehicle;

}
