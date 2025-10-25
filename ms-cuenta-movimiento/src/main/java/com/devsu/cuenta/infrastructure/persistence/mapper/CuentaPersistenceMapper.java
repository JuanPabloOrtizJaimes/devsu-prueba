package com.devsu.cuenta.infrastructure.persistence.mapper;

import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaPersistenceMapper {

    private final ClientePersistenceMapper clientePersistenceMapper;

    public CuentaPersistenceMapper(ClientePersistenceMapper clientePersistenceMapper) {
        this.clientePersistenceMapper = clientePersistenceMapper;
    }

    public CuentaEntity toEntity(Cuenta domain) {
        if (domain == null) {
            return null;
        }
        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setTipoCuenta(domain.getTipoCuenta());
        entity.setSaldoInicial(domain.getSaldoInicial());
        entity.setSaldoActual(domain.getSaldoActual());
        entity.setEstado(domain.getEstado());
        if (domain.getCliente() != null) {
            entity.setCliente(clientePersistenceMapper.toEntity(domain.getCliente()));
        }
        return entity;
    }

    public Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) {
            return null;
        }
        Cuenta domain = new Cuenta();
        domain.setId(entity.getId());
        domain.setNumeroCuenta(entity.getNumeroCuenta());
        domain.setTipoCuenta(entity.getTipoCuenta());
        domain.setSaldoInicial(entity.getSaldoInicial());
        domain.setSaldoActual(entity.getSaldoActual());
        domain.setEstado(entity.getEstado());
        if (entity.getCliente() != null) {
            domain.setCliente(clientePersistenceMapper.toDomain(entity.getCliente()));
            domain.setClienteId(entity.getCliente().getId());
        }
        return domain;
    }
}
