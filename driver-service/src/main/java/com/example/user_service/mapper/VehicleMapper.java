package com.example.user_service.mapper;

import com.example.user_service.dto.CreateDriverDTO;
import com.example.user_service.entity.Driver;

public class VehicleMapper {

    public static Driver toDriver(CreateDriverDTO createDriverDTO){
        Driver driver = new Driver();
        driver.setUserUuid(createDriverDTO.getUserId());
        driver.setLicenseNumber(createDriverDTO.getVehicle().getLicenseNumber());
        driver.setVehicleMake(createDriverDTO.getVehicle().getVehicleMake());
        driver.setVehicleModel(createDriverDTO.getVehicle().getVehicleModel());
        driver.setVehicleName(createDriverDTO.getVehicle().getVehicleName());
        driver.setVehicleColor(createDriverDTO.getVehicle().getVehicleColor());
        return driver;
    }


}
