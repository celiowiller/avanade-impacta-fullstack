package com.agro.sensores.api.dto;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LeituraRequestDTO(
		@NotBlank String sensorId,
		@NotNull Double valor,
		
		@NotNull
		@JsonFormat(shape = JsonFormat.Shape.STRING)
		LocalDateTime dataHora
		) {}
