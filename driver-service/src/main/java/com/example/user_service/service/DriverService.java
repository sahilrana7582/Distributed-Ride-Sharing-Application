package com.example.user_service.service;

import com.example.user_service.dto.CreateDriverDTO;
import com.example.user_service.entity.Driver;
import com.example.user_service.kafka.events.RideAcceptEvent;
import com.example.user_service.kafka.events.RideRequestEvent;
import com.example.user_service.kafka.events.RideStatus;
import com.example.user_service.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@Slf4j
public class DriverService {

    private final DriverRepository driverRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String RIDE_REQUEST_KEY_PREFIX = "ride-request:";

    public Driver createUser(CreateDriverDTO userDTO){
        return driverRepository.save(Driver.builder()
                .userUuid(userDTO.getUserId())
                .licenseNumber(userDTO.getUserId())
                .vehicleMake(userDTO.getUserId())
                .vehicleModel(userDTO.getUserId())
                .vehicleName(userDTO.getUserId())
                .vehicleColor(userDTO.getUserId())
                .build());
    }

    public void storeNewRideRequest(RideRequestEvent rideRequest) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String rideKey = RIDE_REQUEST_KEY_PREFIX + rideRequest.getId();

        String initialStatus = "PENDING";
        ops.set(rideKey, initialStatus, 5, TimeUnit.MINUTES);
    }

    public boolean acceptRideRequest(String requestId, String driverId) {

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String rideKey = RIDE_REQUEST_KEY_PREFIX + requestId;

        // Try to set the ride status to ACCEPTED with this driver ID only if it doesn't exist already
        // This is an atomic operation in Redis
        Boolean isFirstToAccept = ops.setIfAbsent(rideKey, new RideStatus(RideStatus.Status.ACCEPTED, driverId),
                5, TimeUnit.MINUTES);

        if (Boolean.TRUE.equals(isFirstToAccept)) {
            // This driver was the first to accept the ride
            // Send acceptance event to Kafka for further processing
            RideAcceptEvent acceptanceEvent = new RideAcceptEvent(requestId, driverId);
//            kafkaProducer.sendRideAcceptanceEvent(acceptanceEvent);

            return true;
        } else {
            // Ride was already accepted by another driver
            return false;
        }

    }

    public Driver getUser(String id){
        return driverRepository.findById(id).orElse(null);
    }

    public void deleteUser(String id){
        driverRepository.deleteById(id);
    }

    public Driver updateUser(String id, CreateDriverDTO userDTO){
        Driver driver = driverRepository.findById(id).orElse(null);
        driver.setUserUuid(userDTO.getUserId());
        driver.setLicenseNumber(userDTO.getVehicle().getLicenseNumber());
        driver.setVehicleMake(userDTO.getVehicle().getVehicleMake());
        driver.setVehicleModel(userDTO.getVehicle().getVehicleModel());
        driver.setVehicleName(userDTO.getVehicle().getVehicleName());
        driver.setVehicleColor(userDTO.getVehicle().getVehicleColor());
        return driverRepository.save(driver);
    }

}
