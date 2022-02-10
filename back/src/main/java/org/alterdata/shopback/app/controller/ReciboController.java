package org.alterdata.shopback.app.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.alterdata.shopback.app.dto.*;
import org.alterdata.shopback.app.exceptions.EmailException;
import org.alterdata.shopback.app.exceptions.IdException;
import org.alterdata.shopback.app.model.Recibo;
import org.alterdata.shopback.app.repository.ReciboRepository;
import org.alterdata.shopback.app.service.ReciboService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/recibos")
public class ReciboController {

    @Autowired
    ReciboService reciboService;

    @Autowired
    ReciboRepository reciboRepository;

    @Autowired
    private EntityManager entityManager;



    @GetMapping
    @ApiOperation(value = "Retorna todos os recibos cadastrados", notes = "listagem de recibos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os recibos"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Ocorreu uma exceção"),
    })
    public ResponseEntity<List<ReciboDTO>> listar() {
        List<ReciboDTO> recibos = reciboService.listar();
        return ResponseEntity.ok().body(recibos);
    }

    @GetMapping("/buscar/{id}")
    @ApiOperation(value = "Retorna recibo cadastrado", notes = "Recibo por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna recibo"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Ocorreu uma exceção"),
    })
    public ResponseEntity<Object> buscar(@PathVariable @Valid Long id){
        try {
            Optional<Recibo> recibo = reciboService.buscar(id);
            return ResponseEntity.ok().body(recibo);
        }catch(IdException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PostMapping ("/pdf")
    @ApiOperation(value = "Gera recibo pdf cadastrado", notes = "Salva Recibo pdf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna recibo pdf"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Ocorreu uma exceção"),
    })
    public ResponseEntity<InputStreamResource> exportarPdf(@RequestBody ReciboTemplateDTO reciboTemplateDTO)
            throws IOException {
        InputStream arquivoPdf = reciboService.exportarPdf(reciboTemplateDTO);

            return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recibo-" +
                            LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".pdf")
                    .body(new InputStreamResource(arquivoPdf));

    }

    @PostMapping
    @ApiOperation(value = "Cadastra recibo", notes = "Cadastrar recibo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recibo adicionado"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Ocorreu uma exceção"),
    })
    public ResponseEntity<Object> inserir(@RequestBody Recibo recibo){
        try {
            ReciboDTO reciboDTO = reciboService.inserirRecibo(recibo);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand(reciboDTO)
                    .toUri();
            return ResponseEntity.created(uri).body(reciboDTO);
        } catch (EmailException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
