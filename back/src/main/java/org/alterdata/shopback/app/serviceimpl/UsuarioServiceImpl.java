package org.alterdata.shopback.app.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.alterdata.shopback.app.dto.RoleDTO;
import org.alterdata.shopback.app.dto.RoleInserirDTO;
import org.alterdata.shopback.app.dto.UsuarioDTO;
import org.alterdata.shopback.app.dto.UsuarioInserirDTO;
import org.alterdata.shopback.app.exceptions.EmailException;
import org.alterdata.shopback.app.exceptions.IdException;
import org.alterdata.shopback.app.model.Role;
import org.alterdata.shopback.app.model.Usuario;
import org.alterdata.shopback.app.repository.RoleRepository;
import org.alterdata.shopback.app.repository.UsuarioRepository;
import org.alterdata.shopback.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository rolerp;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;  
	
	@Override
    public List<UsuarioDTO> pesquisarTodosUsuario() {
 		List<Usuario> usuarios = usuarioRepository.findAll();
 		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
 		
 		for (Usuario usuario : usuarios) {
 			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario); 
 			usuariosDTO.add(usuarioDTO);
 		}
        return usuariosDTO;
    }
	
	@Override
	public Optional<Usuario> pesquisarUmUsuario(Long id){
		if(!idExisteUsuario(id)) {
			throw new IdException("Usuário não encontrado");
		}
		return usuarioRepository.findById(id);
	}
	
	@Override
	public boolean idExisteUsuario(Long id) {
		return usuarioRepository.existsById(id);		
	}
	
	@Override
	public UsuarioDTO inserirUsuario(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		UsuarioDTO user = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (user != null) {
			throw new EmailException("Usuário já existe.");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(passwordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuario.setTelefone(usuarioInserirDTO.getTelefone());
		usuario = usuarioRepository.save(usuario);

		return new UsuarioDTO(usuario);
		}
	
	@Override
	public void removerIdUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@Override
	public UsuarioDTO atualizarUsuario(Long id , UsuarioInserirDTO usuario){
		Usuario user = usuarioRepository.getById(id);
		if(user == null) {
			throw new IdException("Usuário não encontrado.");
		}
		user.setNome(usuario.getNome());
		user.setEmail(usuario.getEmail());
		user.setSenha(passwordEncoder.encode(usuario.getSenha()));
		user.setTelefone(usuario.getTelefone());
		UsuarioDTO usuarioDTO = new UsuarioDTO(user);
		usuarioRepository.save(usuarioDTO);
		return usuarioDTO;
	}

	//adiciona role a um usuario, por exemplo adicionar o status de admin a um atendente
	@Override
	public void adicionarRole(Long id, RoleInserirDTO role) {
		Usuario us = usuarioRepository.getById(id);
		Role rl =  (Role) rolerp.findByNome(role.getNome());
		us.getRole().add(rl);
	}
	
	//cria uma nova role, por exemplo, cria a role medico, ou o que vc quiser
	@Override
	public RoleDTO inserirRole(RoleInserirDTO role) {
		Role rl = new Role();
		rl.setNome(role.getNome());
		rolerp.save(rl);
		return new RoleDTO (rl);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.getByNome(username);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		Collection <SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRole().forEach(role ->{
		authorities.add(new SimpleGrantedAuthority(role.getNome()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getNome(), user.getSenha(), authorities);
	}

}
