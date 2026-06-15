package com.agro.sensores.domain.models;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Sensor {
	// definir as propriedades/atributos da classe
	private String id;
	private String nome;
	private String localizacao;
	private boolean ativo;	
	private TipoSensor tipo;
	
	public Sensor(
				String id,
				String nome,
				String localizacao,
				boolean ativo,
				TipoSensor tipo
			) {
			// Validações 
			if(nome ==  null || nome.isBlank()) {
				throw new ValidacaoException("Nome do sensor é obrigatório");
			}
		
			if(localizacao ==  null || localizacao.isBlank()) {
				throw new ValidacaoException("Localização é obrigatório");
			}
			
			if(tipo == null) {
				throw new ValidacaoException("Tipo de sensor é obrigatório!");
			}
			
			// inicializar as propriedades
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.ativo = ativo;
		this.tipo = tipo;		
		
	}
	
	// definir um método que permitirá que seja feita qualquer atualização quando estabelecermos 
	// o UseCase correto
	public void alterarLocalizacao(String novaLocalizacao) {
		if(novaLocalizacao == null || novaLocalizacao.isBlank()) {
			throw new ValidacaoException("A nova localização não pode ser vazia!");
		}
		
		// caso o contrario...
		this.localizacao = novaLocalizacao;
	}
	
	// comportamento do dominio
	public void ativar() { this.ativo = true;}
	public void desativar() {this.ativo = false;}
	
	public boolean isAtivo() {return ativo; }	
}
