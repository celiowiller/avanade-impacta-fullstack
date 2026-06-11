package com.agro.sensores.application.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.agro.sensores.domain.enums.UserRole;
import com.agro.sensores.domain.exception.RegraNegocioException;
import com.agro.sensores.domain.models.Usuario;
import com.agro.sensores.domain.repository.UsuarioRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

// esta classe será responsavel por cadastrar usuarios no sistema
// todo o UseCase é, fundamentalmente, um Service 
@Service 
@Validated
/*
 para que o Springframework intercepte a chamada do método e valide os dados 
 antes de executar a lógica de cadastro, nossa classe @Service precisa ser anotada
 com @Validated
 */
@RequiredArgsConstructor
public class CadastrarUsuarioUseCase {
	
	// um usercase é composto por duas situações distintas e complementares:
	// 1. uso de injeção de dependencia
	// 2. método que executará uma tarefa especifica  de operação dos dados!
	// portanto, podemos entender que um usecase - enventualmente - pode ser 
	// compreendido como uma business layer - camada de negocio?
	// R.: Sim, é adequado considera-lo dessa forma;  e tambem, adequadamente, podemos defini-lo
	// ApplicationLayer
	
	// definir a DI a partir do repositorio de dominio
	private final UsuarioRepository repoDomain;
	
	// tambem vamos definir a DI para codificar/encriptar a senha
	private final PasswordEncoder passEncoder;
	
	// método "principal" do usecase
	public void executar(String login, String senha, @NotNull UserRole userRole) {
		// 1º passo: verificar se o valor dado ao parametro login já existe na base
		if(repoDomain.existeLogin(login)) {
			throw new RegraNegocioException("este email/login já está em uso!");
		}
		
		// caso o contrario ocorra....
		// 2º passo: estabelecer a criptografia da senha
		String senhaCriptografada = passEncoder.encode(senha);
		
		// 3º passo: gerar um objeto - a partir do model domain - para armazenar os dados na base
		Usuario usuario = new Usuario(
					null, login, senhaCriptografada, userRole
				);
		
		// 4º passo: salvar o usuario - para que seja armazenado na base
		repoDomain.salvar(usuario);
	}
	
	
	
	
	
	
	
	

}
