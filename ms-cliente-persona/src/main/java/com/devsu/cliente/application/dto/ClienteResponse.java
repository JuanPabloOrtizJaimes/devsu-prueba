package com.devsu.cliente.application.dto;

public class ClienteResponse {

	private Long id;
	private String nombre;
	private String genero;
	private int edad;
	private String identificacion;
	private String direccion;
	private String telefono;
	private boolean estado;

	public ClienteResponse(Long id, String nombre, String genero, int edad, String identificacion, String direccion,
			String telefono, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estado = estado;
	}

	// Getters
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getGenero() {
		return genero;
	}

	public int getEdad() {
		return edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean isEstado() {
		return estado;
	}
}
