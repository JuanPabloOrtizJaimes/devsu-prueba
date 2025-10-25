package com.devsu.cliente.infrastructure.controller;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.devsu.cliente.application.event.RabbitMQEventPublisher;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RabbitMQEventPublisher eventPublisher;

    @Test
    void whenPostValidCliente_thenCreatesClienteAndReturns201() throws Exception {
        
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNombre("Juan Pablo");
        clienteRequest.setGenero("Masculino");
        clienteRequest.setEdad(30);
        clienteRequest.setIdentificacion("1122334455");
        clienteRequest.setDireccion("Avenida Siempre Viva 123");
        clienteRequest.setTelefono("0998765432");
        clienteRequest.setContrasena("supersecret");
        clienteRequest.setEstado(true);

        
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("Cliente creado correctamente"))
                .andExpect(jsonPath("$.data.nombre").value("Juan Pablo"))
                .andExpect(jsonPath("$.data.identificacion").value("1122334455"));
    }

    @Test
    void whenPostInvalidCliente_missingNombre_thenReturns400() throws Exception {
        
        ClienteRequest clienteRequest = new ClienteRequest();
        
        clienteRequest.setGenero("Femenino");
        clienteRequest.setEdad(25);
        clienteRequest.setIdentificacion("5544332211");
        clienteRequest.setDireccion("Calle Falsa 456");
        clienteRequest.setTelefono("0987654321");
        clienteRequest.setContrasena("password");
        clienteRequest.setEstado(true);

        
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteRequest)))
                .andExpect(status().isBadRequest());
    }
}
