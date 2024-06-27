package com.josue.saleserverddd.infrastructure.adapters.inbound.events.rabbitmq;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.infrastructure.adapters.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUserConsumer {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_ON_CREATED_USER)
    public void onCreated(User user) {
        System.out.println("onCreated: " + user);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_ON_EDITED_USER)
    public void onEdited(User user) {
        System.out.println("onEdited: " + user);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_ON_DELETED_USER)
    public void onDeleted(String data) {
        System.out.println("onDeleted: " + data);
    }

}
