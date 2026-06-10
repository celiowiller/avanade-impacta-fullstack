package com.agro.sensores.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

//import lombok.Value;

/* esta classe será nosso "guardião" - da aplicação; irá operar o contexto de segurança
 * baseada em Tokens
 * 
 * Vamos, para este proposito, implementar o conceito JWT (Json Web Token); poderiamos dizer 
 * que este token funciona como se fosse um "crachá";
 * 
 *  essa analogia faz sentido pois este token é composto com as credenciais necessarias para 
 *  a autenticação/autorização de acesso do usuario à areas restritas da aplicação.
 * 
 * */
@Service 
public class TokenService {
	
	// 1º passo: definir a secret-key(chave-secreta) para o token
	// posteriormente, vamos definir a origem/relação desta secret-key 
	// no arquivo application.properties
	//@Value("${api.security.secret:default-dev-secret-123456}") // ${JWT_SECRET}
	@Value("${api.security.secret}")
	private String secret;
	
	// 2º passo: definir o método que executa todas as tarefas para a criação do token
	public String gerarToken(UsuarioEntity usuario) {
		
		// definição e uso do algoritmo de assinatura do token
		Algorithm algoritmo = Algorithm.HMAC256(secret);
		
		// agora, vamos definir o token com as informações do usuario
		return JWT.create()
				.withIssuer("agro-sensores") // este é o emissor do token
				.withSubject(usuario.getLogin()) // este é contexot de dados de login do Usuario
				.withClaim("role", usuario.getRole().name()) // acesso a role do usuario embarcada no 
				// token
				.withExpiresAt(dataExpiracao()) // esta é a data de expiração do token
				.sign(algoritmo); // aqui, a criação do token é "assinada" referenciando 
				// o valor da var algoritmo
		}
	
	// 3º passo: definir o método de data de expiração do token
	private Instant dataExpiracao() {
		// vamos definir a expressão de retorno do método
		return LocalDateTime.now()
				.plusHours(2)
				.toInstant(ZoneOffset.of("-03:00"));
				// acima, estamos definindo a data de validade do token.
		  		// o argumento -03:00 ajusta o horario para o fuso de Brasilia (UTC-3)
	}
	
	// 4º passo: validar e extrair o dado de login do token
	public String getSubject(String token) {
		// declarar o algoritmo de assinatura do token
		Algorithm algoritmo = Algorithm.HMAC256(secret);
		
		return JWT.require(algoritmo)
				.withIssuer("agro-sensores")
				.build()
				.verify(token)
				.getSubject();
	}	
	
}
