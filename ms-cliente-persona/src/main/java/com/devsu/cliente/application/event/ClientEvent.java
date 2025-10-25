package com.devsu.cliente.application.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientEvent<T> implements Serializable {
    private static final long serialVersionUID = -5759505761867396320L;
	private String eventId;
    private LocalDateTime timestamp;
    private String eventType;
    private String source;
    private T data;

    public ClientEvent() {
    }

    public ClientEvent(String eventId, LocalDateTime timestamp, String eventType, String source, T data) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.source = source;
        this.data = data;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}