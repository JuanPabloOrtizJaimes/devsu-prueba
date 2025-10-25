package com.devsu.cliente.application.port.in;

import java.util.List;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.devsu.cliente.application.dto.ClienteResponse;

public interface ClienteUseCase {

	ClienteResponse guardar(ClienteRequest request);

	List<ClienteResponse> obtenerTodos();

	ClienteResponse obtenerPorId(Long id);

	ClienteResponse actualizar(Long id, ClienteRequest request);

	void eliminar(Long id);

}
