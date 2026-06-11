package com.agro.sensores.api.dto;

import com.agro.sensores.domain.enums.TipoSensor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarSensorDTO(
		@NotBlank
		String nome,
		
		@NotBlank
		String localizacao,
		
		@NotNull
		TipoSensor tipo
		) {}
