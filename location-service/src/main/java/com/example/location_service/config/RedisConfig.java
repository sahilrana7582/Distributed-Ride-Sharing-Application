package com.example.location_service.config;


import com.example.location_service.entity.LocationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, LocationEvent> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, LocationEvent> temp = new RedisTemplate<>();
        temp.setConnectionFactory(connectionFactory);
        temp.setKeySerializer(new StringRedisSerializer());
        temp.setValueSerializer(new Jackson2JsonRedisSerializer<LocationEvent>(LocationEvent.class));
        return temp;
    }
}
