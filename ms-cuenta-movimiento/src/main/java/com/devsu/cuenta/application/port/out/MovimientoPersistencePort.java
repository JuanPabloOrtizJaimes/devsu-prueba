package com.devsu.cuenta.application.port.out;

import com.devsu.cuenta.domain.model.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoPersistencePort {

    Movimiento save(Movimiento movimiento);

    Optional<Movimiento> findById(Long id);

    List<Movimiento> findAll();

    void deleteById(Long id);

    Optional<Movimiento> findTopByCuentaIdOrderByFechaDesc(Long cuentaId);

    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin);
}
