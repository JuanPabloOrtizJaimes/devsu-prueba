package com.devsu.cuenta.application.service;

import com.devsu.cuenta.application.port.out.CuentaPersistencePort;
import com.devsu.cuenta.application.port.out.MovimientoPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovimientoServiceConfig {

    @Bean
    public MovimientoServiceImpl movimientoService(MovimientoPersistencePort movimientoPersistencePort, CuentaPersistencePort cuentaPersistencePort) {
        return new MovimientoServiceImpl(movimientoPersistencePort, cuentaPersistencePort);
    }
}
