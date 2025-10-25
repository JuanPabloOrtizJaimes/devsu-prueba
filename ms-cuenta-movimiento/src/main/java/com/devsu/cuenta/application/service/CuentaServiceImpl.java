package com.devsu.cuenta.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsu.cuenta.application.dto.CuentaRequest;
import com.devsu.cuenta.application.dto.CuentaResponse;
import com.devsu.cuenta.application.mapper.CuentaMapper;
import com.devsu.cuenta.application.port.in.CuentaUseCase;
import com.devsu.cuenta.application.port.out.ClientePersistencePort;
import com.devsu.cuenta.application.port.out.CuentaPersistencePort;
import com.devsu.cuenta.domain.model.Cuenta;
import com.devsu.cuenta.infrastructure.exception.BusinessException;
import com.devsu.cuenta.infrastructure.exception.CuentaNotFoundException;

@Service
public class CuentaServiceImpl implements CuentaUseCase {

	private final CuentaPersistencePort cuentaPersistencePort;
    private final ClientePersistencePort clientePersistencePort;


	public CuentaServiceImpl(CuentaPersistencePort cuentaPersistencePort,ClientePersistencePort clientePersistencePort) {
		this.cuentaPersistencePort = cuentaPersistencePort;
		this.clientePersistencePort=clientePersistencePort;
	}

	@Override
	public CuentaResponse crearCuenta(CuentaRequest request) {
		if (cuentaPersistencePort.existsByNumeroCuenta(request.getNumeroCuenta())) {
			throw new BusinessException("Ya existe una cuenta con ese número");
		}
		if (!clientePersistencePort.existsById(request.getClienteId())) {
			throw new BusinessException("Cliente no encontrado");
		}

		Cuenta cuenta = CuentaMapper.toDomain(request);		
		Cuenta nuevaCuenta = cuentaPersistencePort.save(cuenta);
		return CuentaMapper.toResponse(nuevaCuenta);
	}

	@Override
	public List<CuentaResponse> listarCuentas() {
		List<Cuenta> cuentas = cuentaPersistencePort.findAll();
		return CuentaMapper.toResponse(cuentas);
	}

	@Override
	public CuentaResponse obtenerCuentaPorId(Long id) {
		Cuenta cuenta = cuentaPersistencePort.findById(id)
				.orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con id: " + id));
		return CuentaMapper.toResponse(cuenta);
	}

	@Override
	public CuentaResponse actualizarCuenta(Long id, CuentaRequest request) {
		Cuenta cuentaExistente = cuentaPersistencePort.findById(id)
				.orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con id: " + id));

		cuentaExistente.setTipoCuenta(request.getTipoCuenta());
		cuentaExistente.setEstado(request.getEstado());

		Cuenta cuentaActualizada = cuentaPersistencePort.save(cuentaExistente);
		return CuentaMapper.toResponse(cuentaActualizada);
	}

}
