package org.alterdata.shopback.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

import org.alterdata.shopback.app.dto.UsuarioInserirDTO;

@Entity
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
	
	@Column(name = "nome_usuario")
	private String nome;
	
	@Column(name = "email_usuario")
	private String email;
	
	@Column (name = "senha_usuario")
	private String senha;
	
	@Column(name = "telefone")
	private String telefone;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="usuario_role",
			joinColumns={@JoinColumn(name="role_id_role")},
			inverseJoinColumns={@JoinColumn(name="usuario_id_usuario")})
	private Collection<Role> role = new ArrayList<>();
	
	public Usuario() {
		
	}
	
	public Usuario(UsuarioInserirDTO us) {
		this.nome = us.getNome();
		this.email = us.getEmail();
		this.senha = us.getSenha();
		this.telefone = us.getTelefone();
		this.role = us.getRole();
	}

	public Usuario(Long id, String nome, String email, String senha, String telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Role> getRole() {
		return role == null ? new ArrayList<>() : this.role;
	}

	public void setRole(Collection<Role> role) {
		this.role = role;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
}
