package com.josue.saleserverddd.infrastructure.adapters.inbound.events.rabbitmq;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.infrastructure.adapters.config.UserRabbitMqConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqUserConsumer {

    public static final String HEADER_X_RETRIES_COUNT = "x-retries-count";

    private static final Integer MAX_RETRIES_COUNT = 3;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqUserConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @RabbitListener(queues = UserRabbitMqConfig.QUEUE_ON_CREATED_USER)
    public void onCreated(User user) {
        log.info("onCreated: " + user);
    }

    @RabbitListener(queues = UserRabbitMqConfig.QUEUE_ON_EDITED_USER)
    public void onEdited(User user) {
        log.info("onEdited: " + user);
    }

    @RabbitListener(queues = UserRabbitMqConfig.QUEUE_ON_DELETED_USER)
    public void onDeleted(String data,
                          Message message,
                          Channel channel,
                          @Header(AmqpHeaders.DELIVERY_TAG) long tag
    ) throws IOException {
        log.info("onDeleted: " + data);

//        throw new NotFoundEntityException("Example of error on consumer means not acknowledge of the message and requeue automatically");
        channel.basicReject(tag, false);
    }

    @RabbitListener(queues = UserRabbitMqConfig.QUEUE_DL_USER)
    public void rePublish(Message failedMessage,
                          @Header(value = HEADER_X_RETRIES_COUNT, defaultValue = "1") Integer retriesCount
    ) {
        if (retriesCount > MAX_RETRIES_COUNT) {
            log.info("Sending message to the parking lot queue");
            rabbitTemplate.send(UserRabbitMqConfig.QUEUE_PARKING_LOT_USER,
                                failedMessage.getMessageProperties().getReceivedRoutingKey(),
                                failedMessage
            );
            return;
        }
        log.info("Retrying message for the {} time", retriesCount);
        failedMessage.getMessageProperties().getHeaders().put(HEADER_X_RETRIES_COUNT, ++retriesCount);
        failedMessage.getMessageProperties().getHeaders().put("x-delay", 1000 * retriesCount);
        rabbitTemplate.send("",
                            failedMessage.getMessageProperties().getReceivedRoutingKey(),
                            failedMessage
        );
    }

    @RabbitListener(queues = UserRabbitMqConfig.QUEUE_PARKING_LOT_USER)
    public void parkingLot(Message failedMessage) {
        log.info("Received message in parking lot queue {}", failedMessage.toString());
    }

}
