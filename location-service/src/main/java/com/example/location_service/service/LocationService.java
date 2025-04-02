package com.example.location_service.service;


import com.example.location_service.entity.LocationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationService {

    private final RedisTemplate<String, LocationEvent> redisTemplate;
    private final KafkaTemplate<String, LocationEvent> kafkaTemplate;

    private static final String LOCATION_KEY_PREFIX = "driver:location:";


    public void updateDriverLiveLocation(LocationEvent locationEvent) {
        String redisKey = LOCATION_KEY_PREFIX + locationEvent.getRequestId();

        redisTemplate.opsForValue().set(redisKey, locationEvent, 5, TimeUnit.MINUTES);

        kafkaTemplate.send("driver-location-updates", locationEvent.getRequestId(), locationEvent);
        log.info("Location update published for ride: {}", locationEvent.getRequestId());
    }



    public LocationEvent getDriverLocation(String rideId) {
        String redisKey = LOCATION_KEY_PREFIX + rideId;
        return redisTemplate.opsForValue().get(redisKey);
    }

}
