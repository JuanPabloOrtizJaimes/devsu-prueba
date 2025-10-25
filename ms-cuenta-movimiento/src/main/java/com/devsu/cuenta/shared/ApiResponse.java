package com.devsu.cuenta.shared;

import java.time.LocalDateTime;

public class ApiResponse<T> {

	private String mensaje;
	private T data;
	private LocalDateTime timestamp;

	public ApiResponse(String mensaje, T data) {
		this.mensaje = mensaje;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public String getMensaje() {
		return mensaje;
	}

	public T getData() {
		return data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
