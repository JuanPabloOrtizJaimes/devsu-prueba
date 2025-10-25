package com.devsu.cliente.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clientes", uniqueConstraints = { @UniqueConstraint(columnNames = "identificacion") })
public class ClienteEntity extends PersonaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contrasena;

	private boolean estado;

	public ClienteEntity() {
	}

	public ClienteEntity(String nombre, String genero, int edad, String identificacion, String direccion,
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
