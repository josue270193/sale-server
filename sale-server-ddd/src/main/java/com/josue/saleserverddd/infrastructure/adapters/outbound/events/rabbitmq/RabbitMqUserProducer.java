package com.josue.saleserverddd.infrastructure.adapters.outbound.events.rabbitmq;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.events.UserNotification;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUserProducer implements UserNotification {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCreatedUser;
    private final Queue queueEditedUser;
    private final Queue queueDeletedUser;

    public RabbitMqUserProducer(RabbitTemplate rabbitTemplate,
                                Queue queueCreatedUser,
                                Queue queueEditedUser,
                                Queue queueDeletedUser
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueCreatedUser = queueCreatedUser;
        this.queueEditedUser = queueEditedUser;
        this.queueDeletedUser = queueDeletedUser;
    }

    @Override
    public boolean onCreated(User user) {
        rabbitTemplate.convertAndSend(queueCreatedUser.getName(), user);
        return true;
    }

    @Override
    public boolean onEdited(User user) {
        rabbitTemplate.convertAndSend(queueEditedUser.getName(), user);
        return true;
    }

    @Override
    public boolean onDeleted(String id) {
        rabbitTemplate.convertAndSend(queueDeletedUser.getName(), id);
        return true;
    }
}
