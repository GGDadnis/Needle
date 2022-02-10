package org.alterdata.shopback.app.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.alterdata.shopback.app.model.Endereco;
import org.alterdata.shopback.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/{cep}")
	@ApiOperation(value = "Retorna o endereço", notes = "endereço a partir do numero do CEP")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o endereço"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Endereco> buscar(@PathVariable String cep) {
		Endereco enderecoDTO = enderecoService.buscar(cep);
		if (enderecoDTO == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(enderecoDTO);
		}
	}	
}