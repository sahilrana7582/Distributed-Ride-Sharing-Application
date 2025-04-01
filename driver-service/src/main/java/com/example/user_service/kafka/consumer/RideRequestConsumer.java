package com.example.user_service.kafka.consumer;


import com.example.user_service.kafka.events.RideRequestEvent;
import com.example.user_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RideRequestConsumer {

    private final DriverService driverService;


    @Value("${kafka.topic.ride-request}")
    private String rideRequestTopic;


    @KafkaListener(topics = "${kafka.topic.ride-request}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeRideRequestEvent( RideRequestEvent acceptanceData) {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------");
        log.info("Received ride request event: {}", acceptanceData);
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------");

//        driverService.storeNewRideRequest(acceptanceData);
//        driverService.acceptRideRequest(acceptanceData.getId(), "123456");
    }
}
