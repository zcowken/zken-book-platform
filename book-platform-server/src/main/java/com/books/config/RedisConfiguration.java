//package com.books.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Slf4j
//@Configuration
//public class RedisConfiguration {
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        log.info("开始创建redis对象");
//        RedisTemplate redisTemplate = new RedisTemplate();
//        //设置链接的工厂对象
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        //序列化器件-字符串序列化器件
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }
//}
