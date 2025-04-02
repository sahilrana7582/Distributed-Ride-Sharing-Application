package com.example.user_service.controller;


import com.example.user_service.dto.CreateDriverDTO;
import com.example.user_service.entity.Driver;
import com.example.user_service.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/driver")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final DriverService userService;


    @PostMapping
    public Driver createUser(@RequestBody CreateDriverDTO userDTO){
        return userService.createUser(userDTO);
    }



    @PutMapping("/{id}")
    public Driver updateUser(@PathVariable String id, @RequestBody CreateDriverDTO userDTO){
        return userService.updateUser(id, userDTO);
    }


    @PostMapping("/{driverId}/accept-ride/{rideRequestId}")
    public ResponseEntity<?> acceptRide(@PathVariable String driverId,
                                        @PathVariable String rideRequestId) {
        boolean accepted = userService.acceptRideRequest(rideRequestId, driverId);

        if (accepted) {
            return ResponseEntity.ok("Ride request accepted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ride has already been accepted by another driver");
        }
    }

}
