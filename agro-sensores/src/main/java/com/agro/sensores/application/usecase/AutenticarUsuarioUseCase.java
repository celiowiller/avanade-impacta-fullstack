package com.agro.sensores.application.usecase;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.agro.sensores.domain.repository.UsuarioRepository;
import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;
import com.agro.sensores.infra.security.TokenService;

import lombok.RequiredArgsConstructor;

//esta classe será responsavel por cadastrar usuarios no sistema
//todo o UseCase é, fundamentalmente, um Service 
@Service 
@Validated
/*
para que o Springframework intercepte a chamada do método e valide os dados 
antes de executar a lógica de cadastro, nossa classe @Service precisa ser anotada
com @Validated
*/
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {
	
	// um usercase é composto por duas situações distintas e complementares:
	// 1. uso de injeção de dependencia
	// 2. método que executará uma tarefa especifica  de operação dos dados!
	
	// DIs
	// definir o uso do gerenciador de autenticação do Springframework security
	private final AuthenticationManager authManager;
	
	// definir o uso do JpaRepository para usuario
	private final JpaUsuarioRepository jpaRepo;
	
	// definir o uso do token JWT
	private final TokenService tkService;
	
	// método "principal" do usecase
	public String executar(String login, String senha) {
		// criaremos um objeto para o processo de autenticação
		var authToken = new UsernamePasswordAuthenticationToken(login, senha);
		
		// definir, efetivamente, o processo de autenticação do usuario
		authManager.authenticate(authToken);
		
		// agora, precisamos fazer uma pesquisa no DB
		UsuarioEntity usuario = jpaRepo.findByLogin(login)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		// retornar nosso token - a partir da chamada do método gerarToken()
		return tkService.gerarToken(usuario);
	}	

}
