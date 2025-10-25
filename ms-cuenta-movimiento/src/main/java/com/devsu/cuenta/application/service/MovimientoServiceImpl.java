package com.devsu.cuenta.application.service;

import com.devsu.cuenta.application.port.in.MovimientoUseCase;
import com.devsu.cuenta.application.port.out.CuentaPersistencePort;
import com.devsu.cuenta.application.port.out.MovimientoPersistencePort;
import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.infrastructure.exception.BusinessException;
import com.devsu.cuenta.infrastructure.exception.CuentaNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MovimientoServiceImpl implements MovimientoUseCase {

    private final MovimientoPersistencePort movimientoPersistencePort;
    private final CuentaPersistencePort cuentaPersistencePort;

    public MovimientoServiceImpl(MovimientoPersistencePort movimientoPersistencePort, CuentaPersistencePort cuentaPersistencePort) {
        this.movimientoPersistencePort = movimientoPersistencePort;
        this.cuentaPersistencePort = cuentaPersistencePort;
    }

    @Transactional
    @Override
    public Movimiento crear(Movimiento movimiento) {
        if (movimiento.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("El valor del movimiento no puede ser negativo.");
        }

        Cuenta cuenta = cuentaPersistencePort.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada"));

        BigDecimal saldoActualCuenta = Optional.ofNullable(cuenta.getSaldoActual())
                                            .orElse(cuenta.getSaldoInicial());

        BigDecimal valorMovimiento = movimiento.getValor();
        if ("debito".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            valorMovimiento = valorMovimiento.negate();
            if (saldoActualCuenta.add(valorMovimiento).compareTo(BigDecimal.ZERO) < 0) { 
                throw new BusinessException("Saldo no disponible");
            }
        } else if (!"credito".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            throw new BusinessException("Tipo de movimiento no válido. Use 'credito' or 'debito'.");
        }

        movimiento.setFecha(LocalDate.now());
        BigDecimal nuevoSaldoMovimiento = saldoActualCuenta.add(valorMovimiento); 
        movimiento.setSaldo(nuevoSaldoMovimiento);
        movimiento.setCuenta(cuenta);

        cuenta.setSaldoActual(nuevoSaldoMovimiento); 
        cuentaPersistencePort.save(cuenta);

        return movimientoPersistencePort.save(movimiento);
    }

    @Override
    public Movimiento obtenerPorId(Long id) {
        return movimientoPersistencePort.findById(id).orElse(null);
    }

    @Override
    public List<Movimiento> obtenerTodos() {
        return movimientoPersistencePort.findAll();
    }



    @Transactional
    @Override
    public Movimiento anular(Long id) {
        Movimiento movimientoOriginal = movimientoPersistencePort.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Movimiento no encontrado para anular"));

        String tipoMovimientoOpuesto = "debito".equalsIgnoreCase(movimientoOriginal.getTipoMovimiento()) ? "credito" : "debito";

        Movimiento movimientoDeAnulacion = new Movimiento();
        movimientoDeAnulacion.setCuenta(movimientoOriginal.getCuenta());
        movimientoDeAnulacion.setTipoMovimiento(tipoMovimientoOpuesto);
        movimientoDeAnulacion.setValor(movimientoOriginal.getValor());

        return crear(movimientoDeAnulacion);
    }
}
