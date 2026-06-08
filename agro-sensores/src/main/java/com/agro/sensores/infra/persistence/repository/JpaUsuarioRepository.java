package com.agro.sensores.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.UsuarioEntity;

/* conceitualmente, na arquitetura hexagonal, um Repository - na camada INFRA - 
 * é uma PORTA DE SAÍDA (OUTPUT PORT)que representa uma necessidadde do dominio 
 * de persistir ou recuperar dados de uma determinada base - a partir de uma entity
 * 
 * */

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, String> {
	/*
	 * ao praticar a "extensão" com JpaRepository temos a possibilidade de fazer uso 
	 * de uma série de métodos para estabelcer fluxo de dados 
	 * por exemplos:
	 * 
	 * save()
	 * findById()
	 * findAll()
	 * deleteById()
	 * count()
	 * entre outros...
	 * */
	
	// vamos declarar o método para fazer busca de registro de usuario pela propriedade
	// login
	Optional<UsuarioEntity> findByLogin(String login);
	
	
	
	
}
