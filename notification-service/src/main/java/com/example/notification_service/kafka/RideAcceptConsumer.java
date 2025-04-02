package com.example.notification_service.kafka;

import com.example.notification_service.events.RideAcceptEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class RideAcceptConsumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "${kafka.topic.ride-accept}", groupId = "notification_group")
    public void listenRideAccept(RideAcceptEvent event) {  // Changed from String to RideAcceptEvent
        messagingTemplate.convertAndSend("/topic/rideEvents", event);
        System.out.println("Ride Accept event pushed: " + event.getMessage());
    }

    @KafkaListener(topics = "${kafka.topic.ride-cancel}", groupId = "notification_group")
    public void listenRideCancel(RideAcceptEvent event) {  // Changed from String to RideAcceptEvent
        messagingTemplate.convertAndSend("/topic/rideEvents", event);
        System.out.println("Ride Cancel event pushed: " + event.getMessage());
    }

    @KafkaListener(topics = "${kafka.topic.ride-complete}", groupId = "notification_group")
    public void listenRideComplete(RideAcceptEvent event) {  // Changed from String to RideAcceptEvent
        messagingTemplate.convertAndSend("/topic/rideEvents", event);
        System.out.println("Ride Complete event pushed: " + event.getMessage());
    }
}