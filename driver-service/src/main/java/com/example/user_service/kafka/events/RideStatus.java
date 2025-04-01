package com.example.user_service.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideStatus implements Serializable {

    public RideStatus(Status status, String driverId) {
        this.status = status;
        this.driverId = driverId;
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    private Status status;
    private String driverId;
    private LocalDateTime lastUpdated = LocalDateTime.now();

}
