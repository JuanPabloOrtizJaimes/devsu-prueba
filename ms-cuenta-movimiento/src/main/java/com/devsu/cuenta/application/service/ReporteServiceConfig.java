package com.devsu.cuenta.application.service;

import com.devsu.cuenta.application.port.out.ClientePersistencePort;
import com.devsu.cuenta.application.port.out.CuentaPersistencePort;
import com.devsu.cuenta.application.port.out.MovimientoPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReporteServiceConfig {

    @Bean
    public ReporteServiceImpl reporteService(ClientePersistencePort clientePersistencePort, CuentaPersistencePort cuentaPersistencePort, MovimientoPersistencePort movimientoPersistencePort) {
        return new ReporteServiceImpl(clientePersistencePort, cuentaPersistencePort, movimientoPersistencePort);
    }
}
