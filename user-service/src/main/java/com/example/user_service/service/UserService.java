package com.example.user_service.service;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.entity.User;
import com.example.user_service.events.RideRequestEvent;
import com.example.user_service.kafka.RideRequestProducer;
import com.example.user_service.mapper.Mapper;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RideRequestProducer rideRequestProducer;
    private final RedisTemplate<String, Object> redisTemplate;



    private static final String RIDE_REQUEST_TOPIC = "ride-request:";


    public RideRequestEvent sendRideRequestEvent(RideRequestEvent event) {
//        log.info("Creating ride request: {}", event);
//        String redisKey = RIDE_REQUEST_TOPIC + event.getId();
//        redisTemplate.opsForValue().set(redisKey, event);
//        redisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);
//        System.out.println(redisTemplate.keys("*"));
//        log.info("Ride request created: {}", event + "------------------------");
        rideRequestProducer.sendRideRequestEvent(event);
        return event;
    }


    public User createUser(UserDTO userDTO){
        User user = Mapper.toUser(userDTO);
        return userRepository.save(user);
    }

    public User getUser(String id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String userId, UserDTO userDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setPhone(userDTO.getPhone());
        return userRepository.save(user);
    }

}
