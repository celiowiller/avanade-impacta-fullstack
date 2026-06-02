package com.agro.sensores.domain.models;

import com.agro.sensores.domain.enums.UserRole;
import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

// ESTA É A DOMAIN ENTITY - 1º: o que NÃO É esta entity -> NÃO É UMA JPA ENTITY - 
// entidade representativa de tabela do DB 

// uma Entidade de dominio é uma IDENTIDADE UNICA com o seu proprio ciclo de vida; 
// continua sendo unica mesmo que seus atributos, ao longo de um periodo, mudem
/*
  esta classe define as propriedades de dados que serão considerados para a lida com os 
  dados de usuario.
  
  também estamos usando duas annotations - com origem no lombok - que gera, para esta entidade
  as linhas de código necessario para a manipulação dos dados de usuario - tudo isso ocorre 
  em tempo de execução.
  
  uma annotarion nada mais do que: um recurso - aplicado à uma estrutura de código que da à este 
  mesmo código, um novo "papel" dentro da aplicação;
 */

@Getter // esta annotation gera automaticamente o método acessor get() para o encapsulamento de 
// prpriedades private

@ToString(exclude = "senha") // esta annotation gera, automaticamente, o método de asserção/conversão
// de um elemento numa string - toString()
public class Usuario {
	// definir o identificar unico do usuario - id
	private String id;
	
	// login do usuario
	private String login;
	
	// definir o atributo senha 
	private String senha;
	
	// definir a role - nivel de acesso - do usuario
	private UserRole role;
	
	
	// definir o construtor da classe e inicializar as propriedades
	public Usuario(
				String id,
				String login,
				String senha,
				UserRole role
			) {
		// definir alguns "comportamentos"/condições que são proprios da entidade
		
		// verificar o valor da propriedade login
		if(login == null || login.isBlank()) {
			// se a expressão for verdadeira, vamos lançar uma exceção
			throw new ValidacaoException("Sua credencial de login é obrigatoria!");
		}
		
		// verificar o valor da propriedade senha
		if(senha == null || senha.length() < 6){
			// vamos lançar a exceção
			throw new ValidacaoException("Sua senha deve ter pelo menos 6 caracteres!");
		}
		
		// inicicalização da propriedades/atributos de classe
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.role = role;		
	}
	
	// vamos definir um método: o proposito deste metodo é verificar a role do usuario - se ele 
	// é admin ou não 
	
	public boolean isAdmin() {
		return this.role == UserRole.ADMIN;
	}
	
}
