package com.devsu.cuenta.infrastructure.messaging.listener;

import com.devsu.cuenta.application.service.ClientEventService;
import com.devsu.cuenta.infrastructure.messaging.config.RabbitMQConfig;
import com.devsu.cuenta.infrastructure.messaging.event.ClientData;
import com.devsu.cuenta.infrastructure.messaging.event.ClientDeletedData;
import com.devsu.cuenta.infrastructure.messaging.event.ClientEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventListener {

    private final ClientEventService clientEventService;

    public ClientEventListener(ClientEventService clientEventService) {
        this.clientEventService = clientEventService;
    }

    @RabbitListener(queues = RabbitMQConfig.CLIENT_CREATED_QUEUE)
    public void handleClientCreated(ClientEvent<ClientData> event) {
        clientEventService.handleClientCreatedEvent(event.getData());
    }

    @RabbitListener(queues = RabbitMQConfig.CLIENT_UPDATED_QUEUE)
    public void handleClientUpdated(ClientEvent<ClientData> event) {
        clientEventService.handleClientUpdatedEvent(event.getData());
    }

    @RabbitListener(queues = RabbitMQConfig.CLIENT_DELETED_QUEUE)
    public void handleClientDeleted(ClientEvent<ClientDeletedData> event) {
        clientEventService.handleClientDeletedEvent(event.getData());
    }
}
