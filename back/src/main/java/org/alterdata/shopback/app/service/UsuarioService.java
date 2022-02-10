package org.alterdata.shopback.app.service;

import java.util.List;
import java.util.Optional;

import org.alterdata.shopback.app.dto.RoleDTO;
import org.alterdata.shopback.app.dto.RoleInserirDTO;
import org.alterdata.shopback.app.dto.UsuarioDTO;
import org.alterdata.shopback.app.dto.UsuarioInserirDTO;
import org.alterdata.shopback.app.exceptions.EmailException;
import org.alterdata.shopback.app.model.Role;
import org.alterdata.shopback.app.model.Usuario;

public interface UsuarioService {

	UsuarioDTO atualizarUsuario(Long id, UsuarioInserirDTO usuario);

	void removerIdUsuario(Long id);
	
	//adicona role a um usuario
	void adicionarRole(Long id, RoleInserirDTO role);

	boolean idExisteUsuario(Long id);

	Optional<Usuario> pesquisarUmUsuario(Long id);

	List<UsuarioDTO> pesquisarTodosUsuario();

	UsuarioDTO inserirUsuario(UsuarioInserirDTO usuarioInserirDTO) throws EmailException;
	
	//cria nova role
	RoleDTO inserirRole(RoleInserirDTO role);

}
