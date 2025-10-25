package com.devsu.cuenta.infrastructure.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CLIENT_EVENTS_EXCHANGE = "client_events_exchange";
    public static final String CLIENT_CREATED_QUEUE = "ms-cuenta-movimiento.client.created.queue";
    public static final String CLIENT_UPDATED_QUEUE = "ms-cuenta-movimiento.client.updated.queue";
    public static final String CLIENT_DELETED_QUEUE = "ms-cuenta-movimiento.client.deleted.queue";

    public static final String CLIENT_CREATED_ROUTING_KEY = "client.created";
    public static final String CLIENT_UPDATED_ROUTING_KEY = "client.updated";
    public static final String CLIENT_DELETED_ROUTING_KEY = "client.deleted";

    @Bean
    public TopicExchange clientEventsExchange() {
        return new TopicExchange(CLIENT_EVENTS_EXCHANGE);
    }

    @Bean
    public Queue clientCreatedQueue() {
        return new Queue(CLIENT_CREATED_QUEUE, true);
    }

    @Bean
    public Queue clientUpdatedQueue() {
        return new Queue(CLIENT_UPDATED_QUEUE, true);
    }

    @Bean
    public Queue clientDeletedQueue() {
        return new Queue(CLIENT_DELETED_QUEUE, true);
    }

    @Bean
    public Binding bindClientCreated() {
        return BindingBuilder.bind(clientCreatedQueue()).to(clientEventsExchange()).with(CLIENT_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding bindClientUpdated() {
        return BindingBuilder.bind(clientUpdatedQueue()).to(clientEventsExchange()).with(CLIENT_UPDATED_ROUTING_KEY);
    }

    @Bean
    public Binding bindClientDeleted() {
        return BindingBuilder.bind(clientDeletedQueue()).to(clientEventsExchange()).with(CLIENT_DELETED_ROUTING_KEY);
    }

    @Bean
    public org.springframework.amqp.support.converter.MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
