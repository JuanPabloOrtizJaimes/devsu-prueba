package com.devsu.cuenta.infrastructure.persistence.mapper;

import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimientoPersistenceMapper {

    private final CuentaPersistenceMapper cuentaPersistenceMapper;

    public MovimientoPersistenceMapper(CuentaPersistenceMapper cuentaPersistenceMapper) {
        this.cuentaPersistenceMapper = cuentaPersistenceMapper;
    }

    public MovimientoEntity toEntity(Movimiento movimiento) {
        if (movimiento == null) {
            return null;
        }
        MovimientoEntity entity = new MovimientoEntity();
        entity.setId(movimiento.getId());
        entity.setFecha(movimiento.getFecha());
        entity.setTipoMovimiento(movimiento.getTipoMovimiento());
        entity.setValor(movimiento.getValor());
        entity.setSaldo(movimiento.getSaldo());
        if (movimiento.getCuenta() != null) {
            entity.setCuenta(cuentaPersistenceMapper.toEntity(movimiento.getCuenta()));
        }
        return entity;
    }

    public Movimiento toDomain(MovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setId(entity.getId());
        movimiento.setFecha(entity.getFecha());
        movimiento.setTipoMovimiento(entity.getTipoMovimiento());
        movimiento.setValor(entity.getValor());
        movimiento.setSaldo(entity.getSaldo());
        if (entity.getCuenta() != null) {
            movimiento.setCuenta(cuentaPersistenceMapper.toDomain(entity.getCuenta()));
        }
        return movimiento;
    }
}
