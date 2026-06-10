package com.agro.sensores.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agro.sensores.infra.persistence.repository.JpaUsuarioRepository;

import lombok.RequiredArgsConstructor;

// esta classe é responsavel por implementar o serviço de autenticação do usuario
// a partir de suas credencias - armazenadas no DB - que implementamos no UsuarioEntity
@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService{
	// 1. declarar a DI necessario para o procedimento de autenticação 
	private final JpaUsuarioRepository jpa;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		// aqui buscamos o usuario que foi cadastrado no DB- por exemplo: usuario@mail.com
		return jpa.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
	}
}
