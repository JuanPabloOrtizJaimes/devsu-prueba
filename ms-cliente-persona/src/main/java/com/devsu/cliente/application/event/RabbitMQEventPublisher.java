package com.devsu.cliente.application.event;

import com.devsu.cliente.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishClientCreatedEvent(ClientEvent<ClientData> event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CLIENT_EVENTS_EXCHANGE, RabbitMQConfig.CLIENT_CREATED_ROUTING_KEY, event);
    }

    public void publishClientUpdatedEvent(ClientEvent<ClientData> event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CLIENT_EVENTS_EXCHANGE, RabbitMQConfig.CLIENT_UPDATED_ROUTING_KEY, event);
    }

    public void publishClientDeletedEvent(ClientEvent<ClientDeletedData> event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.CLIENT_EVENTS_EXCHANGE, RabbitMQConfig.CLIENT_DELETED_ROUTING_KEY, event);
    }
}
