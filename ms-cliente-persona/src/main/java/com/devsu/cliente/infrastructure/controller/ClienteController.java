package com.devsu.cliente.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.devsu.cliente.application.dto.ClienteResponse;
import com.devsu.cliente.application.service.ClienteService;
import com.devsu.cliente.shared.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<ClienteResponse>> crearCliente(@Valid @RequestBody ClienteRequest request) {
		ClienteResponse response = clienteService.guardar(request);
		return ResponseEntity.status(201).body(new ApiResponse<>("Cliente creado correctamente", response));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<ClienteResponse>>> listarClientes() {
		List<ClienteResponse> lista = clienteService.obtenerTodos();
		return ResponseEntity.ok(new ApiResponse<>("Lista de clientes obtenida correctamente", lista));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ClienteResponse>> obtenerPorId(@PathVariable Long id) {
		return clienteService.obtenerPorId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ClienteResponse>> actualizarCliente(@PathVariable Long id,
			@Valid @RequestBody ClienteRequest request) {
		return clienteService.actualizar(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminarCliente(@PathVariable Long id) {
		return clienteService.eliminar(id);

	}
}
