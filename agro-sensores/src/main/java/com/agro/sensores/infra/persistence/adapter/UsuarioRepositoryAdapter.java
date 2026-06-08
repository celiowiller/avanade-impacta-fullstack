package com.agro.sensores.infra.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.models.Usuario;
import com.agro.sensores.domain.repository.UsuarioRepository;
import com.agro.sensores.infra.persistence.entity.UsuarioEntity;
import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import lombok.RequiredArgsConstructor;

// agora, nosso adapter irá implementar as operações descrita no UsuarioRepository na camada 
// de dominio

// essencialmente, podemos dizer que um adapter tem como objetivo "transformar" o 
// domain <-> entity; este é um elemento FUNDAMENTAL na arquitetura hexagonal
@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepository {
	
	// é uma variavel/propriedade/atributo.. de referencia 
	// para a existencia da DI (Dependency Injection - Injeção de dependencia)
	// aqui, estamos dizendo duas situações principais: 
	// 1. estamos praticando a Inversão de Controle - significa que "quem controla" o que irá
	// acontecer aqui é o recurso jpa
	// 2. que todas as - ou quase tudo - depende daquilo que define o recurso jpa
	private final JpaUsuarioRepository  jpa;
	
	
	// DEFINIR AS OPERAÇÕES DE DADOS
	
	// // operação 1. buscar usuario por Id
	public Optional<Usuario> buscarPorId(String id){
		// agora, precisamos fazer uso da nossa DI
		return jpa.findById(id).map(this::toDomain); // aqui temos os caracteres :: -> significam que 
		// estamos fazendo uma referencia a um método, ou seja, é o method reference  do Java
		
		// esta instrução .map(this::toDomain) o idêntica a esta: .map(entity -> toDomain)
	}
	
	
	// operação 2. buscar usuario por login
	public Optional<Usuario> buscarPorLogin(String login){
		return jpa.findByLogin(login).map(this::toDomain);
	}
	
	// operação 3. salvar um novo registro de usuario
	public void salvar(Usuario usuario) {
		jpa.save(toEntity(usuario));
	}
	
	// operação 4. remover/excluir um usuario devidamente identificado
	public void deletar(String id) {
		jpa.deleteById(id);
	}
	
	// operação 5. verificar a existencia de dados de credenciais de usuario - especificamente
	// o dado de login - e observar se o usuario ja existe no sistema
	public boolean existeLogin(String login) {
		return jpa.findByLogin(login).isPresent();
	}
	
	// ----------------------------------------------------------
	// definir os blocos/processos que executam o mapeamento entre entity <-> domain
	// estes blocos/processos são conhecidos como Mappers - atuam como "tradutores" entre
	// os diferentes "mundos" 
	private Usuario toDomain(UsuarioEntity entity) {
		// é na expressão de retorno do método que esta "tradução/conversão/transformação" se dá
		return new Usuario(
					entity.getId(),
					entity.getLogin(),
					entity.getSenha(),
					entity.getRole()
				);		
	}
	
	// "tradução/conversão/transformação" de domain <-> entity 
	private UsuarioEntity toEntity(Usuario usuario) {
		return new UsuarioEntity(
					usuario.getId(),
					usuario.getLogin(),
					usuario.getSenha(),
					usuario.getRole()
				);
	}
}

/* no dominio, temos: Usuario, UsuarioRepository
 * na infra, temos: UsuarioEntity, JpaUsuarioRepository
 * estes elementos, acima, não possuem qualquer tipo de relação!
 * 
 * AGORA,  O QUE ACONTECE? ACREDITO QUE: "ALGUÉM" PRECISA ESTABELECER UMA "PONTE" ENTRE 
 * ESTE CONTEXTOS QQUE EXISTEM , SÃO FUNDAMENTAIS MAS NÃO SE COMUNICAM!
 * ENTÃO, É NESTE PONTO QUE ENTRA O ADAPTER! 
 * 
 * o nosso adapter atua como um "tradutor" entre:
 * DOMINIO -> PORTA(PORT - DOMAIN REPOSITORY) -> ADAPTER -> JPA -> DB
 */