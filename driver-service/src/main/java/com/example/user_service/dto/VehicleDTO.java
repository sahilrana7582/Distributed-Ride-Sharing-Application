package com.example.user_service.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private String licenseNumber;

    private String vehicleMake;

    private String vehicleModel;

    private String vehicleName;

    private String vehicleColor;

}
