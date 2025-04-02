package com.example.user_service.service;

import com.example.user_service.config.ThreadSchedular;
import com.example.user_service.dto.CreateDriverDTO;
import com.example.user_service.entity.Driver;
import com.example.user_service.events.LocationEvent;
import com.example.user_service.kafka.events.RideRequestEvent;
import com.example.user_service.kafka.events.RideStatus;
import com.example.user_service.kafka.events.RideTest;
import com.example.user_service.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@Slf4j
public class DriverService {

    private final DriverRepository driverRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final KafkaTemplate<String, RideTest> kafkaTemplate;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ThreadSchedular threadSchedular = ThreadSchedular.getInstance();


    private final KafkaTemplate<String, LocationEvent> locationEventKafkaTemplate;
    private boolean keepUpdatingLocation = false;
    private ScheduledExecutorService scheduler = threadSchedular.getScheduler();



    @Value("${kafka.topic.location-updates}")
    private  String locationUpdatesTopic;

//
//    @Autowired
//    private RedisMessageListenerContainer container;// <key, value>

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

        Boolean isFirstToAccept = ops.setIfAbsent(rideKey, new RideStatus(RideStatus.Status.ACCEPTED, driverId),
                5, TimeUnit.MINUTES);

        if (Boolean.TRUE.equals(isFirstToAccept)) {

            RideTest rideTest = new RideTest("ACCEPTED", driverId);
            ProducerRecord<String, RideTest> record = new ProducerRecord<>("ride-accept", requestId, rideTest);
            kafkaTemplate.send("ride-accept", requestId, rideTest);
            keepUpdatingLocation = true;
            startLiveLocationUpdates(driverId);
            return true;
        } else {
            return false;
        }

    }

    private void startLiveLocationUpdates(String driverId) {
        scheduler.scheduleAtFixedRate(() -> {
            if (keepUpdatingLocation) {
                double latitude = getRandomLatitude();
                double longitude = getRandomLongitude();

                LocationEvent locationEvent = new LocationEvent();

                locationEvent.setDriverId(driverId);
                locationEvent.setLat(latitude);
                locationEvent.setLng(longitude);
                locationEvent.setType("DRIVER");
                locationEventKafkaTemplate.send(locationUpdatesTopic, driverId, locationEvent);

                log.info("Live location updated: Driver {} → {}, {}", driverId, latitude, longitude);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }




    public void stopLiveLocationUpdates() {
        keepUpdatingLocation = false;
        log.info("Live location updates stopped.");
    }


    private double getRandomLatitude() {
        return 12.9716 + (Math.random() - 0.5) * 0.1; // Bangalore latitude ±0.05°
    }

    private double getRandomLongitude() {
        return 77.5946 + (Math.random() - 0.5) * 0.1; // Bangalore longitude ±0.05°
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
