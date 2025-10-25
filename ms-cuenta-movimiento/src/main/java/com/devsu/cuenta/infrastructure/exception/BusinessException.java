package com.devsu.cuenta.infrastructure.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 276620549507258671L;

	public BusinessException(String message) {
		super(message);
	}
}
