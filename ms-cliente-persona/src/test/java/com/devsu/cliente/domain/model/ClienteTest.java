package com.devsu.cliente.domain.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ClienteTest {

    @Test
    void givenValidData_whenCreatingCliente_thenStateIsSetCorrectly() {
        
        String nombre = "Jose Lema";
        String genero = "Masculino";
        int edad = 30;
        String identificacion = "1234567890";
        String direccion = "Otavalo sn y principal";
        String telefono = "0987654321";
        Long id = 1L;
        String contrasena = "1234";
        boolean estado = true;

       
        Cliente cliente = new Cliente(nombre, genero, edad, identificacion, direccion, telefono, id, contrasena, estado);

        
        assertThat(cliente.getId()).isEqualTo(id);
        assertThat(cliente.getContrasena()).isEqualTo(contrasena);
        assertThat(cliente.isEstado()).isEqualTo(estado);
        assertThat(cliente.getNombre()).isEqualTo(nombre);
        assertThat(cliente.getIdentificacion()).isEqualTo(identificacion);
    }

    @Test
    void whenUpdatingState_thenGettersReturnUpdatedValue() {
        
        Cliente cliente = new Cliente();
        String nuevaContrasena = "5678";
        boolean nuevoEstado = false;

        
        cliente.setContrasena(nuevaContrasena);
        cliente.setEstado(nuevoEstado);

        
        assertThat(cliente.getContrasena()).isEqualTo(nuevaContrasena);
        assertThat(cliente.isEstado()).isEqualTo(nuevoEstado);
    }
}
