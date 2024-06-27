package com.josue.saleserverddd.infrastructure.adapters.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String QUEUE_ON_CREATED_USER = "user.created";
    public static final String QUEUE_ON_EDITED_USER = "user.edited";
    public static final String QUEUE_ON_DELETED_USER = "user.deleted";

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueCreatedUser() {
        return new Queue(QUEUE_ON_CREATED_USER);
    }

    @Bean
    public Queue queueEditedUser() {
        return new Queue(QUEUE_ON_EDITED_USER);
    }

    @Bean
    public Queue queueDeletedUser() {
        return new Queue(QUEUE_ON_DELETED_USER);
    }

}
