package com.agro.sensores.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.sensores.api.dto.LeituraRequestDTO;
import com.agro.sensores.application.usecase.RegistrarLeituraUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/leitura") // < -- aqui temos o contexto rota/endpoint principal
@RequiredArgsConstructor
public class LeituraController {

	private final RegistrarLeituraUseCase registrarLeitura;
	
	@PostMapping
	public ResponseEntity<Void> registrar(@RequestBody LeituraRequestDTO leitura){
		
		// executar o método a partir de Usecase
		registrarLeitura.executar(leitura.sensorId(), leitura.valor(), leitura.dataHora());
		
		// retorno
		return ResponseEntity.status(201).build();
	}
}
