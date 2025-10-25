package com.devsu.cuenta.application.mapper;

import com.devsu.cuenta.application.dto.MovimientoRequest;
import com.devsu.cuenta.application.dto.MovimientoResponse;
import com.devsu.cuenta.domain.model.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

    public Movimiento toMovimiento(MovimientoRequest movimientoRequest) {
        if (movimientoRequest == null) {
            return null;
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(movimientoRequest.getTipoMovimiento());
        movimiento.setValor(movimientoRequest.getValor());
        return movimiento;
    }

    public MovimientoResponse toMovimientoResponse(Movimiento movimiento) {
        if (movimiento == null) {
            return null;
        }
        return new MovimientoResponse(
                movimiento.getId(),
                movimiento.getFecha(),
                movimiento.getTipoMovimiento(),
                movimiento.getValor(),
                movimiento.getSaldo(),
                movimiento.getCuenta().getNumeroCuenta()
        );
    }
}