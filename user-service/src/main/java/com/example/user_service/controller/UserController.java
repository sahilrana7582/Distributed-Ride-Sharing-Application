package com.example.user_service.controller;


import com.example.user_service.dto.UserDTO;
import com.example.user_service.entity.User;
import com.example.user_service.events.RideRequestEvent;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }

    @PostMapping("/ride-request")
    public RideRequestEvent sendRideRequestEvent(@RequestBody RideRequestEvent event){
        return userService.sendRideRequestEvent(event);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO){
        return userService.updateUser(userId, userDTO);
    }

}
