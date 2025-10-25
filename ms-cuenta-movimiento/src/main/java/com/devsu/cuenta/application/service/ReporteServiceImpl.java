package com.devsu.cuenta.application.service;

import com.devsu.cuenta.application.dto.CuentaReporteDTO;
import com.devsu.cuenta.application.dto.MovimientoReporteDTO;
import com.devsu.cuenta.application.dto.ReporteMovimientosResponse;
import com.devsu.cuenta.application.port.in.ReporteUseCase;
import com.devsu.cuenta.application.port.out.ClientePersistencePort;
import com.devsu.cuenta.application.port.out.CuentaPersistencePort;
import com.devsu.cuenta.application.port.out.MovimientoPersistencePort;
import com.devsu.cuenta.domain.model.Cliente;
import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import com.devsu.cuenta.infrastructure.exception.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteServiceImpl implements ReporteUseCase {

    private final ClientePersistencePort clientePersistencePort;
    private final CuentaPersistencePort cuentaPersistencePort;
    private final MovimientoPersistencePort movimientoPersistencePort;

    public ReporteServiceImpl(ClientePersistencePort clientePersistencePort, CuentaPersistencePort cuentaPersistencePort, MovimientoPersistencePort movimientoPersistencePort) {
        this.clientePersistencePort = clientePersistencePort;
        this.cuentaPersistencePort = cuentaPersistencePort;
        this.movimientoPersistencePort = movimientoPersistencePort;
    }

    @Override
    public ReporteMovimientosResponse generarEstadoCuenta(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        Cliente cliente = clientePersistencePort.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente no encontrado"));

        List<Cuenta> cuentas = cuentaPersistencePort.findByClienteId(clienteId);

        List<CuentaReporteDTO> cuentasReporte = cuentas.stream().map(cuenta -> {
            List<Movimiento> movimientos = movimientoPersistencePort.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
            List<MovimientoReporteDTO> movimientosReporte = movimientos.stream()
                    .map(movimiento -> new MovimientoReporteDTO(
                            movimiento.getFecha(),
                            movimiento.getTipoMovimiento(),
                            movimiento.getValor(),
                            movimiento.getSaldo()
                    ))
                    .collect(Collectors.toList());

            return new CuentaReporteDTO(
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipoCuenta(),
                    cuenta.getSaldoInicial(),
                    cuenta.getEstado(),
                    movimientosReporte
            );
        }).collect(Collectors.toList());

        return new ReporteMovimientosResponse(cliente.getId(), cliente.getNombre(), cuentasReporte);
    }
}
