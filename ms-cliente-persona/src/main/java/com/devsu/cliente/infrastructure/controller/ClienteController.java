package com.devsu.cliente.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.devsu.cliente.application.dto.ClienteResponse;
import com.devsu.cliente.application.port.in.ClienteUseCase;
import com.devsu.cliente.shared.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class ClienteController {

	private final ClienteUseCase clienteUseCase;

	public ClienteController(ClienteUseCase clienteUseCase) {
		this.clienteUseCase = clienteUseCase;
	}

	@Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente en el sistema y lo persiste en la base de datos.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
	})
	@PostMapping
	public ResponseEntity<ApiResponse<ClienteResponse>> crearCliente(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del cliente para crear", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteRequest.class), examples = @ExampleObject(value = "{\"nombre\": \"Jose Lema\", \"genero\": \"Masculino\", \"edad\": 35, \"identificacion\": \"1234567890\", \"direccion\": \"Otavalo sn y principal\", \"telefono\": \"0987654321\", \"contrasena\": \"1234\", \"estado\": true}")))
			@Valid @RequestBody ClienteRequest request) {
		ClienteResponse response = clienteUseCase.guardar(request);
		ApiResponse<ClienteResponse> apiResponse = new ApiResponse<>("Cliente creado correctamente", response);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	@Operation(summary = "Listar todos los clientes", description = "Devuelve una lista de todos los clientes registrados en el sistema.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
	})
	@GetMapping
	public ResponseEntity<ApiResponse<List<ClienteResponse>>> listarClientes() {
		List<ClienteResponse> lista = clienteUseCase.obtenerTodos();
		ApiResponse<List<ClienteResponse>> apiResponse = new ApiResponse<>("Lista de clientes obtenida correctamente",
				lista);
		return ResponseEntity.ok(apiResponse);
	}

	@Operation(summary = "Obtener un cliente por ID", description = "Busca y devuelve un cliente específico basado en su ID.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cliente encontrado"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ClienteResponse>> obtenerPorId(
			@Parameter(description = "ID del cliente a buscar", required = true, example = "1")
			@PathVariable Long id) {
		ClienteResponse response = clienteUseCase.obtenerPorId(id);
		ApiResponse<ClienteResponse> apiResponse = new ApiResponse<>("Cliente encontrado", response);
		return ResponseEntity.ok(apiResponse);
	}

	@Operation(summary = "Actualizar un cliente", description = "Actualiza los datos de un cliente existente basado en su ID.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ClienteResponse>> actualizarCliente(
			@Parameter(description = "ID del cliente a actualizar", required = true, example = "1")
			@PathVariable Long id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del cliente para actualizar", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteRequest.class), examples = @ExampleObject(value = "{\"nombre\": \"Jose Lema\", \"genero\": \"Masculino\", \"edad\": 35, \"identificacion\": \"1234567890\", \"direccion\": \"Otavalo sn y principal\", \"telefono\": \"0987654321\", \"contrasena\": \"1234\", \"estado\": true}")))
			@Valid @RequestBody ClienteRequest request) {
		ClienteResponse response = clienteUseCase.actualizar(id, request);
		ApiResponse<ClienteResponse> apiResponse = new ApiResponse<>("Cliente actualizado correctamente", response);
		return ResponseEntity.ok(apiResponse);
	}

	@Operation(summary = "Eliminar un cliente", description = "Elimina un cliente del sistema basado en su ID.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cliente eliminado correctamente"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminarCliente(
			@Parameter(description = "ID del cliente a eliminar", required = true, example = "1")
			@PathVariable Long id) {
		clienteUseCase.eliminar(id);
		ApiResponse<Void> apiResponse = new ApiResponse<>("Cliente eliminado correctamente", null);
		return ResponseEntity.ok(apiResponse);
	}
}
