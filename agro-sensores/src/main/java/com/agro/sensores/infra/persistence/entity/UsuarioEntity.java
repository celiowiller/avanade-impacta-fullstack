package com.agro.sensores.infra.persistence.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agro.sensores.domain.enums.UserRole;

// todos os recursos oriundos do jakarta.persitence - por sua vez, tem origem no JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//  O "PEDAÇO" INFRA, DO PROJETO, É RESPONSAVEL POR ABRIGAR 
// TODO O CONTEXTO LÓGICO QUE SE "COMUNICA" COM A PARTE EXTERNA À APLICAÇÃO
/*
 * as classes entity - dentro dos pacotes infra/persistence/entity - nada são do que 
 * "entidades" representativas das tables do DB; estas entities irão
 * representar as do db para a aplicação 
 * 
 * 
 * JPA - "desenha" a table - a partir do seus recursos/annotations
 * Hibernate - "executa" este desenho - criando as tables e seu relacionamentos
 * */


// esta é 1ª entity da aplicação 

@Entity
@Table(name = "usuarios")
@Getter
@Setter

@AllArgsConstructor // annotation que gera um construtor com os campos/fields
// da classe - como parametro/argumento

@EqualsAndHashCode(of = "id") // aqui, a annotation indica que o hash precisa ser igual 
// ao indicado no id do usuario
public class UsuarioEntity implements UserDetails{
	
	// 1. adicionar a linha de "RG" da classe
	private static final long serialVersionUID = 1L;
	
	// definir as propriedades da classe/representação das colunas da table
	
	@Id // aqui, definimos, automaticamente, a PK (chave-primaria) da table
	
	@GeneratedValue(strategy = GenerationType.UUID) // aqui, estamos indicando que o Id dos 
	// registros da table serão salvo e incrementados de forma automatizada
	// UUID -> Universally Unique identifier
	private String id;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	// senha criptografada
	@Column(nullable = false)
	private String senha;
	
	// roles do usuario
	@Enumerated(EnumType.STRING) // usado para "mapear" um atributo do tipo Enum numa 
	// entity JPA
	@Column(nullable = false)
	private UserRole role;
	
	// ============= IMPLEMENTAÇÃO DO método getAuthorities() =========
	// precisamos implementar, aqui, o método pois estamos fazendo a implementação da interface
	// UserDetails - aqui,nesta implementação, é que o Security precisa saber qual é o nivel de acesso 
	// do usuario
	
	// nosso método "converte a enum UserRole em permissões que o Spring Security entende.
	
	
	// ?: é operador optional -> significa que qualquer classe pode participar do mecanismo de herança
	// com GrantedAuthority
	//extends: aqui, há um mecanismo de herança; significa que QUALQUER  classe 
	// que herde ou implemente
	// GrantedAuthority -> neste caso, esta classe é a classe-pai/superclasse! 
	
	// estamos dizendo para o método: "retorne todas as premissões do usuario, numa 
	// object Collection que representará as permissões reconhecidas pelo Spring Security
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
		// se nosso usuario for um ADMIN, ele "ganha" todas as permissões.
		// se for apenas USER, "ganha" sosmente a premissão de usuario comum 
		if(this.role == UserRole.ADMIN) {
			return List.of(
				new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("ROLE_USER")
			);
		}else {
			return List.of(
					new SimpleGrantedAuthority("ROLE_USER")
					);
			}
	}
	
	// aqui, vamos definir o construtor-padrão da classe
	public UsuarioEntity() {}
	
	
	// precisamos implementar os métodos de manipulação dos dados para 
	// cadastro e login de usuario
	public String getPassword() {
		return senha;
	}
	public String getUsername() {
		return login;
	}
	
	public boolean isAccountNonExpired() {
		return true; // nossa conta não expirou
	}
	
	public boolean isAccountNonLocked() {
		return true; // nossa conta não é bloqueada
	}
	
	
	public boolean isCredentialsNonExperied() {
		return true; // nossas credenciais não expiram
	}
	
	public boolean isEnabled() {
		return true; // usuario está ativo no sistema ;
	}
	

}
