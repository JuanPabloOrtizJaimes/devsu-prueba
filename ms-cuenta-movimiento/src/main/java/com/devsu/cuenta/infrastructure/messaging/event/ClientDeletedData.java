package com.devsu.cuenta.infrastructure.messaging.event;

public class ClientDeletedData {
    private Long clientId;

    public ClientDeletedData() {
    }

    public ClientDeletedData(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
