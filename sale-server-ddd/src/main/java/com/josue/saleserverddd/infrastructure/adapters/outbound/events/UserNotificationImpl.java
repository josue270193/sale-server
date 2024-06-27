package com.josue.saleserverddd.infrastructure.adapters.outbound.events;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.events.UserNotification;
import com.josue.saleserverddd.infrastructure.adapters.outbound.events.rabbitmq.RabbitMqUserProducer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class UserNotificationImpl implements UserNotification {

    private final RabbitMqUserProducer rabbitmqProducer;

    public UserNotificationImpl(RabbitMqUserProducer rabbitmqProducer) {
        this.rabbitmqProducer = rabbitmqProducer;
    }

    @Override
    public boolean onCreated(User user) {
        rabbitmqProducer.onCreated(user);
        return true;
    }

    @Override
    public boolean onEdited(User user) {
        rabbitmqProducer.onEdited(user);
        return true;
    }

    @Override
    public boolean onDeleted(String id) {
        rabbitmqProducer.onDeleted(id);
        return true;
    }
}
