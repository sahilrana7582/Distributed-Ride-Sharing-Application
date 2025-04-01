package com.example.user_service.mapper;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.entity.User;

public class Mapper {

    public static User toUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .phone(userDTO.getPhone())
                .build();
    }

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .phone(user.getPhone())
                .build();
    }
}
