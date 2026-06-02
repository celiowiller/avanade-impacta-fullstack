package com.agro.sensores.domain.models;

import java.time.LocalDateTime;

import com.agro.sensores.domain.exception.RegraNegocioException;

// o proposito, aqui, é definir propriedades que serão "tratadas" para 
// observar e estabelecer valores de um mesmo sensor quando ele muda de
// posição 
public class SensorLocalizacao {
	
	private String id;
	private String sensorId;
	private String localizacao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	
	// definir o construtor e inicializar as propriedades
	public SensorLocalizacao(
				String id,
				String sensoId,
				String localizacao,
				LocalDateTime dataInicio,
				LocalDateTime dataFim
			) {
		
		if(sensorId == null) {
			throw new RegraNegocioException("Sensor é obrigatório");
			
		}
		
		if(localizacao == null || localizacao.isBlank()) {
			throw new RegraNegocioException("Localização é obrigatoria!");
		}
		
		if(dataInicio == null) {
			throw new RegraNegocioException("Data de inicio é mandatória!");
		}
		
		// inicializar as propriedades
		this.id = id;
		this.sensorId = sensorId;
		this.localizacao = localizacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;		
	}
	
	// método que tem como objetivo "encerrar" a localização atual e dar a possibilidade de iniciar 
	// uma nova 
	public void encerrar(LocalDateTime dataEncerramento) {
		if(this.dataFim != null) {
			throw new RegraNegocioException("Esta localização já foi encerrada"!);
		}
		this.dataFim = dataEncerramento;
	}
	
	
	public boolean isAtivo() {
		return this.dataFim == null;
	}
	
}
