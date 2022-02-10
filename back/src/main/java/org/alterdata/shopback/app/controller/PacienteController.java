package org.alterdata.shopback.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.alterdata.shopback.app.dto.PacienteDTO;
import org.alterdata.shopback.app.dto.PacienteInserirDTO;
import org.alterdata.shopback.app.exceptions.EmailException;
import org.alterdata.shopback.app.service.PacienteService;
import org.apache.commons.io.IOUtils;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	EntityManager entityManager;

	@GetMapping
	@ApiOperation(value = "Retorna todos os pacientes cadastrados", notes = "listagem de pacientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os pacientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public List<PacienteDTO> pesquisarTodos(@RequestParam Boolean isDeleted){
		return pacienteService.pesquisarTodos(isDeleted);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna paciente cadastrado", notes = "Busca paciente por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna paciente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
    public ResponseEntity<Object> buscar(@PathVariable @Valid Long id) {
        if (pacienteService.idExiste(id)) {
            return ResponseEntity.ok(pacienteService.pesquisarUm(id)) ;
        }
        return null;
    }
	
	@PostMapping
	@ApiOperation(value = "Cadastra paciente", notes = "Cadastrar paciente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Paciente adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Object> inserir(@RequestBody @Valid PacienteInserirDTO pacienteInserirDTO){
		try {
			PacienteDTO pacienteDTO = pacienteService.inserirPaciente(pacienteInserirDTO);
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(pacienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(pacienteDTO);
		}catch(EmailException e){
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PutMapping("/{nomePaciente}")
	@ApiOperation(value = "Atualiza paciente", notes = "Atualizar o paciente por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Paciente atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Object> atualizar(@PathVariable String nomePaciente, @RequestBody @Valid PacienteInserirDTO pacienteInserirDTO){
		try {
			PacienteDTO pacienteDTO = pacienteService.atualizarPaciente(nomePaciente, pacienteInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(pacienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(pacienteDTO);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta paciente", notes = "Deletar paciente por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Paciente deletado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public void deletar(@PathVariable @Valid Long id){
		pacienteService.removerId(id);       
	}

	@GetMapping("/nome")

	public ResponseEntity<Object> buscarPorNome(@PathVariable  String nome) {
		try {
			return ResponseEntity.ok(pacienteService.pesquisarUmPorNome(nome));
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

}
