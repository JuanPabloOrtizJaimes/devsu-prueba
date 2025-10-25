package com.devsu.cuenta.infrastructure.controller;

import com.devsu.cuenta.application.dto.ReporteMovimientosResponse;
import com.devsu.cuenta.application.port.in.ReporteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteUseCase reporteUseCase;

    public ReporteController(ReporteUseCase reporteUseCase) {
        this.reporteUseCase = reporteUseCase;
    }

    @Operation(summary = "Generar reporte de estado de cuenta", description = "Genera un reporte detallado del estado de cuenta de un cliente en un rango de fechas específico.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Reporte generado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReporteMovimientosResponse.class),
                            examples = @ExampleObject(value = "{\"clienteId\": 1, \"clienteNombre\": \"Jose Lema\", \"cuentas\": [{\"numeroCuenta\": \"478758\", \"tipoCuenta\": \"Ahorro\", \"saldoInicial\": 2000.00, \"estado\": true, \"movimientos\": [{\"fecha\": \"2023-01-01\", \"tipoMovimiento\": \"credito\", \"valor\": 100.00, \"saldo\": 2100.00}, {\"fecha\": \"2023-01-02\", \"tipoMovimiento\": \"debito\", \"valor\": 50.00, \"saldo\": 2050.00}]}]}}"))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cliente no encontrado o sin movimientos en el rango de fechas")
    })
    @GetMapping
    public ResponseEntity<ReporteMovimientosResponse> generarReporteEstadoCuenta(
            @Parameter(description = "Fecha de inicio del reporte (YYYY-MM-DD)", required = true, example = "2023-01-01")
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @Parameter(description = "Fecha de fin del reporte (YYYY-MM-DD)", required = true, example = "2023-01-31")
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @Parameter(description = "ID del cliente para el reporte", required = true, example = "1")
            @RequestParam("clienteId") Long clienteId) {

        ReporteMovimientosResponse reporte = reporteUseCase.generarEstadoCuenta(fechaInicio, fechaFin, clienteId);
        return ResponseEntity.ok(reporte);
    }
}
