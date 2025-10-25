package com.devsu.cuenta.infrastructure.persistence.repository;

import com.devsu.cuenta.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    Optional<MovimientoEntity> findTopByCuentaIdOrderByFechaDesc(Long cuentaId);

    List<MovimientoEntity> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDate fechaInicio, LocalDate fechaFin);
}
