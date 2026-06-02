package com.agro.sensores.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

// origem do recurso @Schema

// Enum irá definir os "roles/papeis/niveis" de acesso do usuario - ao sistema


/*
  {
  	role : "ADMIN" -> o valor textual -> ADMIN("admin") USER("user")
  }
 */
@Schema(description = "Enum para definir os papeis/niveis/roles do usuario", 
			allowableValues = {"ADMIN", "USER"})
public enum UserRole {
	// precisamos, dentro  da enum, definir as roles: para este proposit será necessario
	// criar alguns Schemas para a descrição/definição da roles
	@Schema(description = "Administrador")ADMIN("admin"),
	
	// nosso novo schema definirá o usuario com menos privilegios
	@Schema(description = "Usuário comum")USER("user");
	
	// definir o campo/field/propiedade/atributo que irá armazenar o valor textual da role
	private String role;
	
	// definir o construtor e inicializar a propriedade
	UserRole(String role){
		this.role = role; 
	}
	
	
	// definir o método acessor - get - para obter  valor textual da role para a, então, 
	// serialização json
	@JsonValue
	@Schema(description = "Obter o valor textual da role para a serialização")
	public String getRole() {
		return role;
	}
	
	
	// definir um método de desserialização do valor da propriedade role 
	@JsonCreator
	public static UserRole fromRole(String role) {
		for(UserRole r: values()) {
			// vamos verificar o valor do parametro/iterador r
			if(r.role.equalsIgnoreCase(role)) {
				return r;
			}			
		}
		throw new IllegalArgumentException("Role inválida: " + role);
	}
}
