package com.agro.sensores.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SensorResponseDTO(
		// 1º ponto de analise: aqui estão os dados que serão enviados de um-lado-para-o-outro
		// da aplicação
		// aqui, não precisamos das validações, aplicadas aos dados porque, no momento em que 
		// foram armazenados na base, estes mesmos dados - que, qui, estão sendo "acessados" -  já 
		// foram validados!
		String id,
		String nome,
		String localizacao,
		String tipo,
		boolean ativo,
		List<LocalizacaoResponseDTO> historico,
		List<TelemetriaResponseDTO> leituras		
		) {
	// estes DTOs, definidos aqui dentro do DTO SensorResponseDTO, são chamados de 
	// "nested records" (records aninhados)
	public record LocalizacaoResponseDTO(
				String localizacao,
				LocalDateTime dataInicio,
				LocalDateTime dataFIm
			) {}
	public record TelemetriaResponseDTO(
				Double valor,
				LocalDateTime dataHora
			) {}
	
}
