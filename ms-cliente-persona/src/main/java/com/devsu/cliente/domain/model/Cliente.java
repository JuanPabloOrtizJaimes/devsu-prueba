package com.devsu.cliente.domain.model;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {

	private static final long serialVersionUID = -5947758185913143399L;

	private Long id;

	private String contrasena;

	private boolean estado;

	public Cliente() {
	}

	public Cliente(String nombre, String genero, int edad, String identificacion, String direccion,
			String telefono, Long id, String contrasena, boolean estado) {
		super(nombre, genero, edad, identificacion, direccion, telefono);
		this.id = id;
		this.contrasena = contrasena;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
