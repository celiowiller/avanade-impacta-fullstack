package com.agro.sensores.domain.models;

import java.time.LocalDateTime;

import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString()
public class Leitura {
	private Long id;
	private Sensor sensor;
	private Double valor;
	private LocalDateTime dataHora;
	private String localizacao;
	
	// definir o construtor da classe
	public Leitura(
			 Long id, 
			 Sensor sensor,
			 Double valor,
			 LocalDateTime dataHora,
			 String localizacao
			) {
			if(sensor == null) {
				throw new ValidacaoException("Sensor é obrigatorio");
			}
			if(valor == null) {
				throw new ValidacaoException("Valor da leitura é obrigatorio");
			}
			
			if(dataHora == null) {
				throw new ValidacaoException("Data/hora é obrigatoria!");
			}
		
		// inicializar as propriedades
		this.id = id;
		this.sensor = sensor;
		this.valor = valor;
		this.dataHora = dataHora;
		this.localizacao = localizacao;		
	}
		
	 public Sensor getSensor() {
		 return sensor;
	 }
}
