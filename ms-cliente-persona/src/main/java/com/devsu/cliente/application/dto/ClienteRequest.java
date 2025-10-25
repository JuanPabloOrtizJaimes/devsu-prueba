package com.devsu.cliente.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteRequest {

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
	private String nombre;

	@NotBlank(message = "El género es obligatorio")
	private String genero;

	@NotNull(message = "La edad es obligatoria")
	@Min(value = 0, message = "La edad no puede ser negativa")
	private Integer edad;

	@NotBlank(message = "La identificación es obligatoria")
	private String identificacion;

	@NotBlank(message = "La dirección es obligatoria")
	private String direccion;

	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(regexp = "\\d{7,10}", message = "El teléfono debe tener entre 7 y 10 dígitos")
	private String telefono;

	@NotBlank(message = "La contraseña es obligatoria")
	private String contrasena;

	@NotNull(message = "El estado es obligatorio")
	private Boolean estado;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
