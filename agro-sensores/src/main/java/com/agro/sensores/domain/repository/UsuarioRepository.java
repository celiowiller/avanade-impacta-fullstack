package com.agro.sensores.domain.repository;

import java.util.Optional;

import com.agro.sensores.domain.models.Usuario;

//esta interface NÃO É uma interface de repositorio do JPA

// aqui, nesta interafce, o "dominio" esta "dizendo" que precisa destas operações! 
// mas como elas serão implementadas? o Dominio não diz !!!!

// agora, como estas operações se darão.... será responsabilidade de outro pedaço do projeto
public interface UsuarioRepository {
	// vamos declarar uma operação de dados 
	Optional<Usuario> buscarPorId(String id);
	
	// buscar por login
	Optional<Usuario> buscarPorLogin(String login);
	
	//salvar usuario
	void salvar(Usuario usuario);
	
	// remover o registro
	void deletar(String id);

	//verifcar se o usuario existe no sistema
	boolean existeLogin(String login);
}
