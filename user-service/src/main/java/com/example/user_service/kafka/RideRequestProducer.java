package com.example.user_service.kafka;


import com.example.user_service.events.RideRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RideRequestProducer {

    private final KafkaTemplate<String, RideRequestEvent> kafkaTemplate;

    @Value("${kafka.topic.ride-request}")
    private String rideRequestTopic;

    public void sendRideRequestEvent(RideRequestEvent event) {
        log.info("Sending ride request event {}", event);
        kafkaTemplate.send(rideRequestTopic, event);
        log.info("Ride request event {} sent", event);
    }






}
