package com.devsu.cuenta.infrastructure.exception;

public class CuentaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1735159422276468366L;

	public CuentaNotFoundException(String mensaje) {
		super(mensaje);
	}
}