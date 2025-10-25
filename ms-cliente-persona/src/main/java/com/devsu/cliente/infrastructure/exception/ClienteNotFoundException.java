package com.devsu.cliente.infrastructure.exception;

public class ClienteNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7590202322993459686L;

	public ClienteNotFoundException(String message) {
		super(message);
	}
}
