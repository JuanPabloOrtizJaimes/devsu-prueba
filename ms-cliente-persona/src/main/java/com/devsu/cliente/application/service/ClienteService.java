package com.devsu.cliente.application.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.devsu.cliente.application.dto.ClienteResponse;
import com.devsu.cliente.shared.ApiResponse;

public interface ClienteService {

	ClienteResponse guardar(ClienteRequest request);

	List<ClienteResponse> obtenerTodos();

	ResponseEntity<ApiResponse<ClienteResponse>> obtenerPorId(Long id);

	ResponseEntity<ApiResponse<ClienteResponse>> actualizar(Long id, ClienteRequest request);

	ResponseEntity<ApiResponse<Void>> eliminar(Long id);

}
