package com.devsu.cuenta.application.dto;

import java.math.BigDecimal;

public class CuentaResponse {

	private Long id;
	private String numeroCuenta;
	private String tipoCuenta;
	private BigDecimal saldoInicial;
	private Boolean estado;
	private Long clienteId;
	private String nombreCliente;

	private BigDecimal saldoActual;

	public CuentaResponse(Long id, String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, BigDecimal saldoActual, Boolean estado, Long clienteId, String nombreCliente) {
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.saldoActual = saldoActual;
		this.estado = estado;
		this.clienteId = clienteId;
		this.nombreCliente = nombreCliente;
	}

	public BigDecimal getSaldoActual() {
		return saldoActual;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public Boolean getEstado() {
		return estado;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}
}
