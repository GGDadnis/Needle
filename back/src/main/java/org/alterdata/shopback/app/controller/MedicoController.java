package org.alterdata.shopback.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.alterdata.shopback.app.dto.MedicoDTO;
import org.alterdata.shopback.app.exceptions.EmailException;
import org.alterdata.shopback.app.exceptions.IdException;
import org.alterdata.shopback.app.model.Medico;
import org.alterdata.shopback.app.model.Recibo;
import org.alterdata.shopback.app.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping
	@ApiOperation(value = "Retorna todos os medicos cadastrados", notes = "listagem de medicos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os medicos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public List<MedicoDTO> pesquisarTodos(@RequestParam boolean isDeleted ) {
		return medicoService.pesquisarTodos(isDeleted);
	}

	@GetMapping("/buscar/{id}")
	@ApiOperation(value = "Retorna medico cadastrado", notes = "Medico por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna medico"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Object> buscar(@PathVariable @Valid Long id){
		try {
			Medico medico = medicoService.buscar(id);
			MedicoDTO medicoDTO = new MedicoDTO(medico);
			return ResponseEntity.ok().body(medicoDTO);
		}catch(IdException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PostMapping("/cadastrar/inserir")
	@ApiOperation(value = "Cadastra medico", notes = "Cadastrar medico")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Medico adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Object> inserir(@RequestBody Medico medico){
		try {
			MedicoDTO medicoDTO = medicoService.inserirMedico(medico);
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(medicoDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(medicoDTO);
		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PutMapping("/cadastrar/atualizar")
	@ApiOperation(value = "Atualiza medico", notes = "Atualizar o medico por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Medico atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Object> atualizar(@RequestParam String nomeMedico, @RequestBody @Valid Medico medico){
			try {
				MedicoDTO medicoDTO = medicoService.atualizarMedico(nomeMedico, medico);
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(medicoDTO.getId())
						.toUri();
				return ResponseEntity.created(uri).body(medicoDTO);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().body(e.getMessage());
			}
	}
	
	@DeleteMapping("/cadastrar/{id}")
	@ApiOperation(value = "Deleta medico", notes = "Deletar medico por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Medico deletado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public void deletar(@PathVariable @Valid Long id) {
		medicoService.removerId(id);
	}
	
}
