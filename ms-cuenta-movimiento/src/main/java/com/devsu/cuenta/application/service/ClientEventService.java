package com.devsu.cuenta.application.service;

import com.devsu.cuenta.application.port.out.ClientePersistencePort;
import com.devsu.cuenta.domain.model.Cliente;
import com.devsu.cuenta.infrastructure.messaging.event.ClientData;
import com.devsu.cuenta.infrastructure.messaging.event.ClientDeletedData;
import org.springframework.stereotype.Service;

@Service
public class ClientEventService {

    private final ClientePersistencePort clientePersistencePort;

    public ClientEventService(ClientePersistencePort clientePersistencePort) {
        this.clientePersistencePort = clientePersistencePort;
    }

    public void handleClientCreatedEvent(ClientData clientData) {
        Cliente cliente = new Cliente(clientData.getId(), clientData.getNombre());
        clientePersistencePort.save(cliente);
    }

    public void handleClientUpdatedEvent(ClientData clientData) {
        Cliente cliente = new Cliente(clientData.getId(), clientData.getNombre());
        clientePersistencePort.save(cliente);
    }

    public void handleClientDeletedEvent(ClientDeletedData clientDeletedData) {        
        clientePersistencePort.findById(clientDeletedData.getClientId())
                .ifPresent(cliente -> clientePersistencePort.deleteById(cliente.getId()));
    }
}
