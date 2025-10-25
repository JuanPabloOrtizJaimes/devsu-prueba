package com.devsu.cuenta.application.port.in;

import com.devsu.cuenta.application.dto.ReporteMovimientosResponse;

import java.time.LocalDate;

public interface ReporteUseCase {
    ReporteMovimientosResponse generarEstadoCuenta(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);
}
