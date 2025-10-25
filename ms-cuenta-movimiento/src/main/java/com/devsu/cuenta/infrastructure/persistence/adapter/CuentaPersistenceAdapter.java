package com.devsu.cuenta.infrastructure.persistence.adapter;

import com.devsu.cuenta.application.port.out.CuentaPersistencePort;
import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.infrastructure.persistence.entity.CuentaEntity;
import com.devsu.cuenta.infrastructure.persistence.mapper.CuentaPersistenceMapper;
import com.devsu.cuenta.infrastructure.persistence.repository.ClienteRepository;
import com.devsu.cuenta.infrastructure.persistence.repository.CuentaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CuentaPersistenceAdapter implements CuentaPersistencePort {

    private final CuentaRepository cuentaRepository;
    private final CuentaPersistenceMapper cuentaPersistenceMapper;
    private final ClienteRepository clienteRepository;

    public CuentaPersistenceAdapter(CuentaRepository cuentaRepository, CuentaPersistenceMapper cuentaPersistenceMapper, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaPersistenceMapper = cuentaPersistenceMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        CuentaEntity cuentaEntity = cuentaPersistenceMapper.toEntity(cuenta);
        if (cuenta.getClienteId() != null) {
            clienteRepository.findById(cuenta.getClienteId()).ifPresent(cuentaEntity::setCliente);
        }
        CuentaEntity savedEntity = cuentaRepository.save(cuentaEntity);
        return cuentaPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.findAll().stream()
                .map(cuentaPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return cuentaRepository.findById(id).map(cuentaPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta).map(cuentaPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    @Override
    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId).stream()
                .map(cuentaPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
