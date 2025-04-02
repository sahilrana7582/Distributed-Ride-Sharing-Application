package com.example.location_service.controller;


import com.example.location_service.entity.LocationEvent;
import com.example.location_service.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/driver/update")
    public ResponseEntity<Void> updateDriverLocation(@RequestBody LocationEvent location) {
        locationService.updateDriverLiveLocation(location);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/driver/{rideId}")
    public ResponseEntity<LocationEvent> getDriverLocation(@PathVariable String rideId) {
        LocationEvent location = locationService.getDriverLocation(rideId);
        if (location != null) {
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }
}