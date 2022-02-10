package org.alterdata.shopback.app.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.alterdata.shopback.app.dto.RoleInserirDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;
	
	@Column(name = "nome_role")
	private String nome;

	@JsonIgnore
	@ManyToMany(mappedBy="role", cascade = CascadeType.ALL)
	private List<Usuario> usuarios = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public Role(RoleInserirDTO rl) {
		super();
		this.nome = rl.getNome();
	}

	public Role(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Role() {
		super();
	}


	public List<Usuario> getUsuarios() {
		return usuarios == null ?  new ArrayList<>() : this.usuarios;
	}

}
