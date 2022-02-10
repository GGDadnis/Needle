package org.alterdata.shopback.app.dto;

import org.alterdata.shopback.app.model.Role;

public class RoleDTO {

	private String nome;
	private Long id;
	
	public RoleDTO(Role role) {
		this.nome = role.getNome();
		this.id = role.getId();
	}

	public RoleDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}
}
