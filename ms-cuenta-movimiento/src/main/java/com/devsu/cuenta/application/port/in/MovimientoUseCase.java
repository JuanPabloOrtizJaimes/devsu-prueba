package com.devsu.cuenta.application.port.in;

import com.devsu.cuenta.domain.model.Movimiento;

import java.util.List;

public interface MovimientoUseCase {

    Movimiento crear(Movimiento movimiento);

    Movimiento obtenerPorId(Long id);

    List<Movimiento> obtenerTodos();

    Movimiento anular(Long id);
}
