package org.alterdata.shopback.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.alterdata.shopback.app.dto.UsuarioDTO;
import org.alterdata.shopback.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public UsuarioDTO findByEmail(String email);
	public Usuario findByNome(String nome);
	public Usuario save(UsuarioDTO u);
	public Usuario getByNome(String nome);
}
