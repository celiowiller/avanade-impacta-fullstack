package com.agro.sensores.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * Nosso SecurityFilter assume um "papel de segurança" da porta da aplicação;
 * significa que qualquer requisição que estiver sendo feita para as APIS, aqui, 
 * será interceptada para que seja possivel verificar "quem" está fazendo esta 
 * requisição e se ele pode acessar determinado endpoint; portanto, este codigo 
 * é o coração da segurança das APIs springboot que estamos implementando
 * */
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter{
	
	// definindo as DIs
	private final TokenService tokenService;
	private final JpaUsuarioRepository repository;
	
	// este é o método que assume o "papel de filtro de segurança" das APIs
	// por aqui terão de passar todas as requisições HTTP - portanto, estas requisições 
	// aqui, serão interceptadas
	@Override 
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain
			) throws ServletException, IOException {
		
		// vamos, neste momento, definir uma prop para receber como valor o token
		String token = recuperarToken(request);
		
		// verificar o valor da prop token
		if(token != null) {
			
			// agora, precisamos obter o dados de subject do token e, tambem, fazer 
			// uma busca no dado de login - a partir do Jparepository
			// portanto, aqui, vamos fazer uso das DIs
			String subject = tokenService.getSubject(token); 
			var login = repository.findByLogin(subject);
			
			// verificando se a var login, realmente, recebeu algum valor
			if(login.isPresent()) {
				// se recebeu, acessamos este valor e geramos um objeto que será nomeado como 
				// authentication
				var usuario = login.get();
				// aqui, temos o objeto de autenticação - com origem no springsecurity
				// onde passamos o usuario, depois - null - parametro de senha e o método 
				// getAuthotities() com as roles - definido em UsuarioEntity
				var authentication = new UsernamePasswordAuthenticationToken(
							usuario, // credencial de usuario
							null, // credencial de senha 
							usuario.getAuthorities() // método que está "dizendo" ao security 
							// quais são as premissões - roles - do usuario 
						);
				
				SecurityContextHolder.getContext()
						.setAuthentication(authentication); // esta é a linha que, efettivamente, 
				// realiza o "login" do usuario - a partir da requisição estabelecida. Então estamos 
				// "guardando" o objeto authentication dentro do Contexto "mantenedor" de segurança
				// do Spring; é, a partir deste momento que o Spring sabe quem está "logando"
				// e se ele tem permissão para acessar o endpoint requisitado
				
			}// aqui, fecha o if interno
		}// aqui, fecha o if principal
		filterChain.doFilter(request, response); // aqui, é linha que diz que o "trabalho esta 
		// feito"; ou seja, é a linha segue com a requisição - para o endpoint adequado (response) - e 
		// fica aguardando a proxima requisição (request)
		
	}
	
	
	// vamos definir o método recuperarToken()
	private String recuperarToken(HttpServletRequest request) {
		// 1. buscar o "header/cabeçalho" da requisição - Authorization
		String authorizationHeader = request.getHeader("Authorization");
		
		// 2. se a var authorizationHeader estiver "vazia" ou não começar com "Beaer" 
		// vamos retornar nulo
		if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
			return null;
		}
		
		// 3. caso o contrario ocorra... remover a string Bearer e ficar somente com o codigo 
		// criptografado - que na verdade, é o token gerado
		return authorizationHeader.substring(7); 
	}
	
	// este é um exemplo de token gerado
	// Authorization: Bearer asdfgsJKOIjçlkMKJHJbhdbdfjjkh3489798567
	
	
	
	
	
	
	
	
	
	
}
