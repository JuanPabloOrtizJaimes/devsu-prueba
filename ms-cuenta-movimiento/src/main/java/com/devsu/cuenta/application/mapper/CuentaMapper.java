package com.devsu.cuenta.application.mapper;

import com.devsu.cuenta.application.dto.CuentaRequest;
import com.devsu.cuenta.application.dto.CuentaResponse;
import com.devsu.cuenta.domain.model.Cuenta;

import java.util.List;
import java.util.stream.Collectors;

public class CuentaMapper {

    public static Cuenta toDomain(CuentaRequest request) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setSaldoActual(request.getSaldoInicial()); 
        cuenta.setEstado(request.getEstado());
        cuenta.setClienteId(request.getClienteId());
        return cuenta;
    }

    public static CuentaResponse toResponse(Cuenta cuenta) {
        return new CuentaResponse(
                cuenta.getId(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta(),
                cuenta.getSaldoInicial(),
                cuenta.getSaldoActual(),
                cuenta.getEstado(),
                cuenta.getClienteId(),
                cuenta.getCliente() != null ? cuenta.getCliente().getNombre() : null
        );
    }

    public static List<CuentaResponse> toResponse(List<Cuenta> cuentas) {
        return cuentas.stream().map(CuentaMapper::toResponse).collect(Collectors.toList());
    }
}
