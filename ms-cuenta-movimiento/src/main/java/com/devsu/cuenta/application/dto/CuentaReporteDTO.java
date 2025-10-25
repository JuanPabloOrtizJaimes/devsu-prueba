package com.devsu.cuenta.application.dto;

import java.math.BigDecimal;
import java.util.List;

public class CuentaReporteDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private List<MovimientoReporteDTO> movimientos;

    public CuentaReporteDTO(String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, Boolean estado, List<MovimientoReporteDTO> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimientos = movimientos;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<MovimientoReporteDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoReporteDTO> movimientos) {
        this.movimientos = movimientos;
    }
}
