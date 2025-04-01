package com.example.user_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "drivers")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_uuid", nullable = false, unique = true)
    private String userUuid;

    @Column(name = "license_number", nullable = false)
    private String licenseNumber;

    @Column(name = "vehicle_make", nullable = false)
    private String vehicleMake;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "vehicle_plate", nullable = false)
    private String vehicleName;

    @Column(name = "vehicle_color", nullable = false)
    private String vehicleColor;

}
