//package com.geek.config;
//
//import Employee;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.net.UnknownHostException;
//
///**
// * @author : Jeffersonnn
// * @date : 2020/2/3
// * @description :
// */
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<Object, Employee> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<>(Employee.class);
//        template.setDefaultSerializer(serializer);
//        return template;
//    }
//}
