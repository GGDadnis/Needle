package org.alterdata.shopback.app.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.alterdata.shopback.app.model.Email;
import org.alterdata.shopback.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    @ApiOperation(value = "Enviar Email", notes = "Enviar Email por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Email Enviado"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Ocorreu uma exceção"),
    })
    public ResponseEntity<Email> enviar (@RequestParam Long id, @RequestBody Email email) throws MessagingException, IOException {

        emailService.enviarEmail(email, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
