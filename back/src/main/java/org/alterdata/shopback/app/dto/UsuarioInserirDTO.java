package org.alterdata.shopback.app.dto;

import java.util.Collection;

import javax.validation.constraints.NotBlank;

import org.alterdata.shopback.app.model.Role;

public class UsuarioInserirDTO {
	
	@NotBlank(message = "{nome.not.blank}")
	private String nome;
	
	@NotBlank(message = "{email.not.blank}")
	private String email;
	
	@NotBlank(message = "{senha.not.blank}")
	private String senha;
	
	@NotBlank(message = "{telefone.not.blank}")
	private String telefone;
	
	private Collection<Role> role;

	
	public UsuarioInserirDTO() {
		
	}
	
	public UsuarioInserirDTO(String nome, String email, String senha,String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		}


	public Collection<Role> getRole() {
		return role;
	}

	public void setRole(Collection<Role> role) {
		this.role = role;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
