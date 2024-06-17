package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        var serverConfig = new RedisStandaloneConfiguration(redisHost, redisPort);
        serverConfig.setPassword(redisPassword);
        return new LettuceConnectionFactory(serverConfig);
    }

    @Bean
    public RedisTemplate<String, UserEntity> redisUserTemplate(RedisConnectionFactory connectionFactory,
                                                               ObjectMapper objectMapperOriginal
    ) {
        var objectMapper = objectMapperOriginal.copy();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
                                           ObjectMapper.DefaultTyping.NON_FINAL_AND_ENUMS
        );

        var template = new RedisTemplate<String, UserEntity>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        return template;
    }

}
