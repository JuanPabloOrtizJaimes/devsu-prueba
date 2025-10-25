package com.devsu.cuenta.infrastructure.controller;

import com.devsu.cuenta.application.dto.MovimientoRequest;
import com.devsu.cuenta.application.dto.MovimientoResponse;
import com.devsu.cuenta.application.mapper.MovimientoMapper;
import com.devsu.cuenta.application.port.in.MovimientoUseCase;
import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.domain.model.Movimiento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoUseCase movimientoUseCase;
    private final MovimientoMapper movimientoMapper;

    public MovimientoController(MovimientoUseCase movimientoUseCase, MovimientoMapper movimientoMapper) {
        this.movimientoUseCase = movimientoUseCase;
        this.movimientoMapper = movimientoMapper;
    }

    @Operation(summary = "Crear un nuevo movimiento", description = "Registra un nuevo movimiento (crédito o débito) en una cuenta específica.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Movimiento creado exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o saldo no disponible"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PostMapping
    public ResponseEntity<MovimientoResponse> crearMovimiento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del movimiento a crear", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoRequest.class), examples = @ExampleObject(value = "{\"tipoMovimiento\": \"credito\", \"valor\": 100.00, \"cuentaId\": 1}")))
            @Valid @RequestBody MovimientoRequest request) {
        Movimiento movimiento = movimientoMapper.toMovimiento(request);
        Cuenta cuenta = new Cuenta();
        cuenta.setId(request.getCuentaId());
        movimiento.setCuenta(cuenta);
        Movimiento nuevoMovimiento = movimientoUseCase.crear(movimiento);
        return new ResponseEntity<>(movimientoMapper.toMovimientoResponse(nuevoMovimiento), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un movimiento por ID", description = "Busca y devuelve un movimiento específico basado en su ID.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Movimiento encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponse> obtenerMovimiento(
            @Parameter(description = "ID del movimiento a buscar", required = true, example = "1")
            @PathVariable Long id) {
        Movimiento movimiento = movimientoUseCase.obtenerPorId(id);
        if (movimiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimientoMapper.toMovimientoResponse(movimiento));
    }

    @Operation(summary = "Listar todos los movimientos", description = "Devuelve una lista de todos los movimientos registrados en el sistema.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<MovimientoResponse>> obtenerTodosLosMovimientos() {
        List<Movimiento> movimientos = movimientoUseCase.obtenerTodos();
        List<MovimientoResponse> responses = movimientos.stream()
                .map(movimientoMapper::toMovimientoResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Anular un movimiento", description = "Anula un movimiento existente creando un movimiento inverso para mantener la trazabilidad.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Movimiento anulado exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movimiento no encontrado para anular")
    })
    @PostMapping("/{id}/anulacion")
    public ResponseEntity<MovimientoResponse> anularMovimiento(
            @Parameter(description = "ID del movimiento a anular", required = true, example = "1")
            @PathVariable Long id) {
        Movimiento movimientoAnulado = movimientoUseCase.anular(id);
        return ResponseEntity.ok(movimientoMapper.toMovimientoResponse(movimientoAnulado));
    }
}
