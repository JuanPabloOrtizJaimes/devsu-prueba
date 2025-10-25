package com.devsu.cuenta.infrastructure.persistence.adapter;

import com.devsu.cuenta.application.port.out.MovimientoPersistencePort;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.infrastructure.persistence.mapper.MovimientoPersistenceMapper;
import com.devsu.cuenta.infrastructure.persistence.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoPersistenceAdapter implements MovimientoPersistencePort {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoPersistenceMapper movimientoPersistenceMapper;

    public MovimientoPersistenceAdapter(MovimientoRepository movimientoRepository, MovimientoPersistenceMapper movimientoPersistenceMapper) {
        this.movimientoRepository = movimientoRepository;
        this.movimientoPersistenceMapper = movimientoPersistenceMapper;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        var entity = movimientoPersistenceMapper.toEntity(movimiento);
        var savedEntity = movimientoRepository.save(entity);
        return movimientoPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return movimientoRepository.findById(id).map(movimientoPersistenceMapper::toDomain);
    }

    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll().stream()
                .map(movimientoPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public Optional<Movimiento> findTopByCuentaIdOrderByFechaDesc(Long cuentaId) {
        return movimientoRepository.findTopByCuentaIdOrderByFechaDesc(cuentaId)
                .map(movimientoPersistenceMapper::toDomain);
    }

    @Override
    public List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin).stream()
                .map(movimientoPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}
