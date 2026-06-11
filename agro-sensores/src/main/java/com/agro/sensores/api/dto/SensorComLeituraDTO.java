package com.agro.sensores.api.dto;

import java.util.List;

import com.agro.sensores.domain.models.Leitura;
import com.agro.sensores.domain.models.SensorLocalizacao;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SensorComLeituraDTO(
		String id,
		String nome,
		
		@JsonProperty("localizacaoAtual") // previnindo alguma propriedade - enviada pelo front
		// com este tipo de nomenclatura
		String localizacao,
		List<Leitura> leituras,
		
		// adicionando, aqui, o historico para que o dashboard - do front - 
		// posso exibir os dados, tambem, em detalhes 
		List<SensorLocalizacao> historico
		) {}
