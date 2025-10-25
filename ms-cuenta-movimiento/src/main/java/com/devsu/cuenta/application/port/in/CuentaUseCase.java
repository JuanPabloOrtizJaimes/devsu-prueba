package com.devsu.cuenta.application.port.in;

import java.util.List;

import com.devsu.cuenta.application.dto.CuentaRequest;
import com.devsu.cuenta.application.dto.CuentaResponse;

public interface CuentaUseCase {

    CuentaResponse crearCuenta(CuentaRequest request);

    List<CuentaResponse> listarCuentas();

    CuentaResponse obtenerCuentaPorId(Long id);

    CuentaResponse actualizarCuenta(Long id, CuentaRequest request);


}
