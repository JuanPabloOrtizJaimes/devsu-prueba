package com.devsu.cliente.application.event;

import java.io.Serializable;

public class ClientDeletedData implements Serializable {
    private static final long serialVersionUID = -4459219458665399123L;
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