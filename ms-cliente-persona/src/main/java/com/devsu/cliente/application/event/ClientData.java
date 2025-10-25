package com.devsu.cliente.application.event;

import java.io.Serializable;

public class ClientData implements Serializable {
    private static final long serialVersionUID = 8587793034845397559L;
	private Long id;
    private String nombre;

    public ClientData() {
    }

    public ClientData(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}