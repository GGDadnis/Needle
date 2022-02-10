package org.alterdata.shopback.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.alterdata.shopback.app.dto.RoleDTO;
import org.alterdata.shopback.app.dto.RoleInserirDTO;
import org.alterdata.shopback.app.dto.UsuarioDTO;
import org.alterdata.shopback.app.dto.UsuarioInserirDTO;
import org.alterdata.shopback.app.exceptions.EmailException;
import org.alterdata.shopback.app.model.Usuario;
import org.alterdata.shopback.app.repository.UsuarioRepository;
import org.alterdata.shopback.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/cadastro/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository repository;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		List<UsuarioDTO> usuarios = usuarioService.pesquisarTodosUsuario();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Object> buscar(@PathVariable @Valid Long id){
		try {
			Optional <Usuario> user = usuarioService.pesquisarUmUsuario(id);
			
			return ResponseEntity.ok().body(user);
		}catch(EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PostMapping("/inserir")
	public ResponseEntity<Object> inserirUsuario(@RequestBody @Valid UsuarioInserirDTO usuarioInserirDTO){
		try {
			UsuarioDTO dto = usuarioService.inserirUsuario(usuarioInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest(
					).path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
			}
		}

	@PutMapping("/alterar/{nome}")
	public ResponseEntity<Object> AlterarUsuario(@PathVariable String nome, @RequestBody @Valid UsuarioInserirDTO usuarioInserirDTO){
		try {
			UsuarioDTO usuarioDTO = usuarioService.atualizarUsuario(nome, usuarioInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(usuarioDTO.getId())
					.toUri();
			return ResponseEntity.ok().body(usuarioDTO);
		}catch(EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PostMapping("/role/inserir")
	//cria uma nova role
	public ResponseEntity<Object> inserirRole(@RequestBody @Valid RoleInserirDTO role){
		RoleDTO roles = new RoleDTO();
		roles = usuarioService.inserirRole(role);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest(
				).path("/{id}")
				.buildAndExpand(roles.getId()).toUri();
		return ResponseEntity.ok().body(role);
	}
	
	@PostMapping("role/adicionar/{id}")
	//adiciona role a usuario
	public ResponseEntity<Object> adicionarRole(@PathVariable Long id, @RequestBody @Valid RoleInserirDTO role ){
		usuarioService.adicionarRole(id, role);
		return ResponseEntity.ok().body(null);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta usuario", notes = "Deletar usuario por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "usuario deletado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public void deletar(@PathVariable @Valid Long id){
		usuarioService.removerIdUsuario(id);
	}

	@GetMapping("/verifica")
	public Boolean Verifica(){
		return true;
	};
}