package com.agro.sensores.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.sensores.api.dto.UsuarioRequestDTO;
import com.agro.sensores.application.usecase.CadastrarUsuarioUseCase;

import lombok.RequiredArgsConstructor;

/*
 * o controller será responsavel por "controlar" o fluxo de dados da aplicação
 * 
 * este controller, por exemplo, será responsavel por "manipular" os dados de 
 * cadastro de usuario.
 * */

// ABAIXO, ESTAMOS DEFININDO UMA REQUISIÇÃO HTTP, PORTANTO, SEJA QUAL A RESPOSTA - A PARTIR DESTA 
// REQUISIÇÃO - TEM DE, NECESSARIAMENTE, SER UMA RESPOSTA HTTP
@RestController // annotation que "transforma" esta classe java numa estrutura de controle da 
// API
@RequestMapping("/cadastro") // determina o endpoint que é definido para este controller
@RequiredArgsConstructor
public class CadastroController {
	
	// 1. definir a referencia - objeto referencial - para a DI
	private final CadastrarUsuarioUseCase cadastro;
	
	// 2. definir o método para a operação de cadastro
	@PostMapping // annotation que nos da possibilidade de fazer uma requisição, post, 
	// para envio de dados para a base
	public ResponseEntity<Void> cadastrar(@RequestBody UsuarioRequestDTO registro){
		
		// agora, o java "reconhece" todos os dados, inclusive as roles de usuario, 
		// e o método, que vamos definir abaixo, vai executar as operações do nosso UseCase
		cadastro.executar(registro.login(), registro.senha(), registro.role());
		
		// retornamos o status da requisição
		return ResponseEntity.status(201).build();
	}
	
}
