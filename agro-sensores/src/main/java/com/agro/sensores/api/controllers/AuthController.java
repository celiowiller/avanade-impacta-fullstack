package com.agro.sensores.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.sensores.api.dto.LoginRequestDTO;
import com.agro.sensores.api.dto.TokenResponseDTO;
import com.agro.sensores.application.usecase.AutenticarUsuarioUseCase;

import lombok.RequiredArgsConstructor;

// controller de autenticação do usuario
@RestController
@RequestMapping("/auth") // < -- aqui temos o contexto rota/endpoint principal
@RequiredArgsConstructor
public class AuthController {
	private final AutenticarUsuarioUseCase auth;
	
	// agora, vamos definir o método de execução da operação de autenticação
	// mas vamos, tambem, definir um endpoint especifico para este proposito
	@PostMapping("/login") // < -- aqui está o contexto rota/endpoit especifico
	// que irá compor com o endpoint principal
	public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO logando){
		
		// executando a autenticação 
		String token = auth.executar(logando.login(), logando.senha());
		
		// precisamos retornar o token
		return ResponseEntity.ok(new TokenResponseDTO(token));
	}
	
	
	/* ResponseEntity<>: ele representa uma resposta à uma requisição HTTP - resposta completa.
	 *esta classe nos permite controlar, por exemplo: 
	 *o corpo da resposta (body)
	 *o codigo de status da resposta: 200, 201, 404, 500, etc.
	 *o cabeçalhos HTTP (header)
	 * o método - definido com ResponseEntity - diz que: "vou retornar uma resposta HTTP 
	 * cujo corpo contem um objeto do tipo TokenResponseDTO
	 * */
	
}
