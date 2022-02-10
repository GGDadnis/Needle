package org.alterdata.shopback.app.dto;

import java.io.Serializable;

import org.alterdata.shopback.app.model.Usuario;

public class UsuarioDTO implements Serializable{
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();

		this.telefone = usuario.getTelefone();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	
	
}
