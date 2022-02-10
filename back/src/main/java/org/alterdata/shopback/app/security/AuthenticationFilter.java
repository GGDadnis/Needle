package org.alterdata.shopback.app.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter (AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try{
			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");
			UsernamePasswordAuthenticationToken uspsToken = new UsernamePasswordAuthenticationToken(nome, senha);
			//response.setHeader("teste", String.valueOf(uspsToken));
			return authenticationManager.authenticate(uspsToken);
		}catch (Exception e){
			throw new  RuntimeException();
		}

	}

//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		try{
//	UserPasswordAuthRequest userPasswordAuthRequest = new ObjectMapper()
//				.readValue(request.getInputStream(), UserPasswordAuthRequest.class);
//
//			Authentication authentication = new UsernamePasswordAuthenticationToken(
//					userPasswordAuthRequest.getNome(),
//					userPasswordAuthRequest.getSenha()
//			);
//			return authenticationManager.authenticate(authentication);
//		}catch (IOException e){
//			throw new  RuntimeException();
//		}
//	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("segredinho".getBytes());
		String tokenAcesso = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000*60))
				.withIssuer(request.getRequestURL()
						.toString()).withClaim("roles", user.getAuthorities()
								.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		String tokenRefresh = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000*60))
				.withIssuer(request.getRequestURL()
						.toString()).withClaim("roles", user.getAuthorities()
								.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		Map<String, String> tokens = new HashMap<>();
		tokens.put("tokenacesso", tokenAcesso);
		tokens.put("tokenrefresh", tokenRefresh);
		response.setContentType(APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(),tokens);
	}
	

	
}
