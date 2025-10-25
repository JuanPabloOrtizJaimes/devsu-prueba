package com.devsu.cuenta.infrastructure.persistence.mapper;

import com.devsu.cuenta.domain.model.Cliente;
import com.devsu.cuenta.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientePersistenceMapper {

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null) {
            return null;
        }
        ClienteEntity entity = new ClienteEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        Cliente domain = new Cliente();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        return domain;
    }
}
