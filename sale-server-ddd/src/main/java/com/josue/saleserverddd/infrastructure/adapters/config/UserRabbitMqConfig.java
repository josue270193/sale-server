package com.josue.saleserverddd.infrastructure.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRabbitMqConfig {

    public static final String EXCHANGE_PARKING_LOT_USER = "user.exchange.parking-lot";
    public static final String QUEUE_PARKING_LOT_USER = "user.parking-lot";
    public static final String EXCHANGE_DL_USER = "user.dlx";
    public static final String QUEUE_DL_USER = "user.dlq";
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
        return QueueBuilder.durable(QUEUE_ON_CREATED_USER)
                .withArgument("x-dead-letter-exchange", EXCHANGE_DL_USER)
                .build();
    }

    @Bean
    public Queue queueEditedUser() {
        return QueueBuilder.durable(QUEUE_ON_EDITED_USER)
                .withArgument("x-dead-letter-exchange", EXCHANGE_DL_USER)
                .build();
    }

    @Bean
    public Queue queueDeletedUser() {
        return QueueBuilder.durable(QUEUE_ON_DELETED_USER)
                .withArgument("x-dead-letter-exchange", EXCHANGE_DL_USER)
                .build();
    }

    @Bean
    public FanoutExchange exchangeDeadLetterUser() {
        return new FanoutExchange(EXCHANGE_DL_USER);
    }

    @Bean
    public Queue queueDeadLetterUser() {
        return QueueBuilder.durable(QUEUE_DL_USER)
                .build();
    }

    @Bean
    public Binding bindingDeadLetterUser() {
        return BindingBuilder.bind(queueDeadLetterUser())
                .to(exchangeDeadLetterUser());
    }

    @Bean
    public FanoutExchange exchangeParkingLotUser() {
        return new FanoutExchange(EXCHANGE_PARKING_LOT_USER);
    }

    @Bean
    public Queue queueParkingLotUser() {
        return QueueBuilder.durable(QUEUE_PARKING_LOT_USER)
                .build();
    }

    @Bean
    public Binding bindingParkingLotUser() {
        return BindingBuilder.bind(queueParkingLotUser())
                .to(exchangeParkingLotUser());
    }

}
