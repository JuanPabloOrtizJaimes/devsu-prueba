package com.devsu.cliente.infrastructure.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsu.cliente.shared.ApiResponse;

@RestControllerAdvice(basePackages = "com.devsu.cliente.infrastructure.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleClienteNotFound(ClienteNotFoundException ex) {
		ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Object>> handleBusinessException(BusinessException ex) {
		ApiResponse<Object> response = new ApiResponse<>(ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

		ApiResponse<Object> response = new ApiResponse<>("Error de validación", errores);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
		ApiResponse<Object> response = new ApiResponse<>("Error interno del servidor", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
