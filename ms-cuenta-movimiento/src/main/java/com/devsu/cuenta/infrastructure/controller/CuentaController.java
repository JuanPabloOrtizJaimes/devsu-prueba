package com.devsu.cuenta.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cuenta.application.dto.CuentaRequest;
import com.devsu.cuenta.application.dto.CuentaResponse;
import com.devsu.cuenta.application.port.in.CuentaUseCase;
import com.devsu.cuenta.shared.ApiResponse; // Import ApiResponse

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name = "Cuentas", description = "Gestión de cuentas de usuario, incluyendo su creación, consulta y actualización.")
public class CuentaController {

	private final CuentaUseCase cuentaUseCase;

	public CuentaController(CuentaUseCase cuentaUseCase) {
		this.cuentaUseCase = cuentaUseCase;
	}

	@Operation(summary = "Crear una nueva cuenta", description = "Crea una nueva cuenta en el sistema para un cliente existente.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	@PostMapping
	public ResponseEntity<ApiResponse<CuentaResponse>> crearCuenta(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la cuenta para crear", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaRequest.class), examples = @ExampleObject(value = "{\"numeroCuenta\": \"478758\", \"tipoCuenta\": \"Ahorro\", \"saldoInicial\": 2000.00, \"estado\": true, \"clienteId\": 1}")))
			@RequestBody CuentaRequest request) {
		CuentaResponse response = cuentaUseCase.crearCuenta(request);
		return new ResponseEntity<>(new ApiResponse<>("Cuenta creada exitosamente", response), HttpStatus.CREATED);
	}

	@Operation(summary = "Listar todas las cuentas", description = "Devuelve una lista de todas las cuentas registradas en el sistema.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida exitosamente",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = ApiResponse.class)))
	})
	@GetMapping
	public ResponseEntity<ApiResponse<List<CuentaResponse>>> listarCuentas() {
		List<CuentaResponse> responses = cuentaUseCase.listarCuentas();
		return ResponseEntity.ok(new ApiResponse<>("Lista de cuentas obtenida exitosamente", responses));
	}

	@Operation(summary = "Obtener una cuenta por ID", description = "Busca y devuelve una cuenta específica basada en su ID.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cuenta encontrada",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CuentaResponse>> obtenerCuentaPorId(
			@Parameter(description = "ID de la cuenta a buscar", required = true, example = "1")
			@PathVariable Long id) {
		CuentaResponse response = cuentaUseCase.obtenerCuentaPorId(id);
		return ResponseEntity.ok(new ApiResponse<>("Cuenta encontrada", response));
	}

	@Operation(summary = "Actualizar una cuenta", description = "Actualiza los datos de una cuenta existente basado en su ID.")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cuenta actualizada correctamente",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = ApiResponse.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
	})
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CuentaResponse>> actualizarCuenta(
			@Parameter(description = "ID de la cuenta a actualizar", required = true, example = "1")
			@PathVariable Long id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la cuenta para actualizar", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaRequest.class), examples = @ExampleObject(value = "{\"numeroCuenta\": \"478758\", \"tipoCuenta\": \"Ahorro\", \"saldoInicial\": 2000.00, \"estado\": true, \"clienteId\": 1}")))
			@RequestBody CuentaRequest request) {
		CuentaResponse response = cuentaUseCase.actualizarCuenta(id, request);
		return ResponseEntity.ok(new ApiResponse<>("Cuenta actualizada correctamente", response));
	}

}


