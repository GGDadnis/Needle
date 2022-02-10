package org.alterdata.shopback.app;

import org.alterdata.shopback.app.dto.PacienteInserirDTO;
import org.alterdata.shopback.app.dto.RoleInserirDTO;
import org.alterdata.shopback.app.dto.UsuarioDTO;
import org.alterdata.shopback.app.dto.UsuarioInserirDTO;
import org.alterdata.shopback.app.model.Endereco;
import org.alterdata.shopback.app.model.Role;
import org.alterdata.shopback.app.model.Usuario;
import org.alterdata.shopback.app.repository.EnderecoRepository;
import org.alterdata.shopback.app.repository.RoleRepository;
import org.alterdata.shopback.app.repository.UsuarioRepository;
import org.alterdata.shopback.app.service.EnderecoService;
import org.alterdata.shopback.app.service.PacienteService;
import org.alterdata.shopback.app.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;


@SpringBootApplication
public class AlterdatashopbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlterdatashopbackApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UsuarioService userservice, EnderecoService endServ, RoleRepository rolerp, PacienteService pacserv, EnderecoRepository endrep, UsuarioRepository userrp) {
		return args -> {
			carregarDadosDB(userservice, endServ, rolerp, pacserv, endrep, userrp);
		};

	}

	private void carregarDadosDB(UsuarioService userservice, EnderecoService endServ, RoleRepository rolerp, PacienteService pacserv, EnderecoRepository endrep, UsuarioRepository userrp) {
		// Verifica e cadastra se as Roles não estiverem cadastra.
		if (!rolerp.existsById(1L) && !rolerp.existsById(2L)) {
			userservice.inserirRole(new RoleInserirDTO("USER"));
			userservice.inserirRole(new RoleInserirDTO("ADMIN"));
		}

		// Verifica e cadastra se o usuário admin não estiver cadastrado.
		if (!userservice.idExisteUsuario(1L)) {
			UsuarioInserirDTO usuarioInserirDTO = new UsuarioInserirDTO("admin", "dadnis@gmail.com", "123", "99999999");
			userservice.inserirUsuario(usuarioInserirDTO);
			Usuario usuario = new Usuario(usuarioInserirDTO);
		}

		// Verifica e cadastra se o usuário user não estiver cadastrado.
		if (!userservice.idExisteUsuario(2L)) {
			UsuarioInserirDTO usuarioInserirDTO = new UsuarioInserirDTO("user", "vitornicodemus@gmail.com", "123", "99999999");
			userservice.inserirUsuario(usuarioInserirDTO);
			Usuario usuario = new Usuario(usuarioInserirDTO);
			usuario.setRole(Arrays.asList(rolerp.getById(2L)));
		}

		// Verifica e cadastra vincula as roles USER,ADMIN no usuário 1.
		userrp.findById(1L).ifPresent(user ->
		{
			if (user.getRole() == null || user.getRole().isEmpty()) {
				user.setRole(Arrays.asList(
						new Role(1L, "USER"),
						new Role(2L, "ADMIN")
				));
				userrp.saveAndFlush(user);
			}
		});

		// Verifica e vincula a role USER no usuário 2.
		userrp.findById(2L).ifPresent(user -> {
			if (user.getRole() == null || user.getRole().isEmpty()) {
				user.setRole(Arrays.asList(new Role(1L, "USER")));
				userrp.saveAndFlush(user);
			}
		});

	}
}
