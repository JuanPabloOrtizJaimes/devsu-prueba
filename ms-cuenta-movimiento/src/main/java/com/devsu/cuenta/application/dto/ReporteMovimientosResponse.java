package com.devsu.cuenta.application.dto;

import java.util.List;

public class ReporteMovimientosResponse {
    private Long clienteId;
    private String clienteNombre;
    private List<CuentaReporteDTO> cuentas;

    public ReporteMovimientosResponse(Long clienteId, String clienteNombre, List<CuentaReporteDTO> cuentas) {
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.cuentas = cuentas;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public List<CuentaReporteDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaReporteDTO> cuentas) {
        this.cuentas = cuentas;
    }
}
