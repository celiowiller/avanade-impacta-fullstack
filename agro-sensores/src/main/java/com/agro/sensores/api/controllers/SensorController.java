package com.agro.sensores.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.sensores.api.dto.AtualizarLocalizacaoDTO;
import com.agro.sensores.api.dto.AtualizarSensorDTO;
import com.agro.sensores.api.dto.CriarSensorDTO;
import com.agro.sensores.api.dto.SensorComLeituraDTO;
import com.agro.sensores.api.dto.SensorResponseDTO;
import com.agro.sensores.application.usecase.AtualizarLocalizacaoSensorUseCase;
import com.agro.sensores.application.usecase.AtualizarSensorUseCase;
import com.agro.sensores.application.usecase.CadastrarSensorUseCase;
import com.agro.sensores.application.usecase.ListarSensoresComLeituraUseCase;
import com.agro.sensores.application.usecase.ListarSensoresUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sensores") // < -- aqui temos o contexto rota/endpoint principal
@RequiredArgsConstructor
public class SensorController {
	
	// DIs
	private final CadastrarSensorUseCase cadastrarSensor;
	private final ListarSensoresUseCase listarSensores;	
	private final AtualizarSensorUseCase atualizarSensor;	
	private final ListarSensoresComLeituraUseCase listarComLeituraSensor;	
	private final AtualizarLocalizacaoSensorUseCase atualizarLocalizacaoSensor;
	
	// 1. CRIAR SENSOR - para criar um sensor o usuario precisa ter uma permissão ADMIN
	// este contexto de restrição é definido pela annotation @PreAuthorize
	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> criar(@RequestBody CriarSensorDTO criarSensor){
		// acessando a DI para cadastrar o sensor
		cadastrarSensor.executar(criarSensor);
		return ResponseEntity.status(201).build();
	}
	
	// 2. LISTAR SENSORES
	@GetMapping
	public ResponseEntity<List<SensorResponseDTO>> listar(){
		return ResponseEntity.ok(listarSensores.executar());
	}
	
	// 3. BUSCAR/LISTA SENSOR POR ID
	@GetMapping("/{id}") // /sensores/1
	public ResponseEntity<SensorResponseDTO> buscarPorId(@PathVariable String id){
		return ResponseEntity.ok(listarSensores.buscarPorId(id));
	}
	
	// 4. ATUALIZAR SENSOR
	@PutMapping("/{id}") // precisamos compor a rota com id
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> atualizar(
			@PathVariable String id, // esta annotation permite que um elemento variavel componha a rota
			@RequestBody AtualizarSensorDTO atualizar ){
		
		// acessando a DI para cadastrar o sensor
		atualizarSensor.executar(id, atualizar.nome());
		return ResponseEntity.ok().build();
	}
	
	
	// 5. EXCLUIR SENSOR
	@DeleteMapping("/{id}") // rota composta com o identificador do registro
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletar(@PathVariable String id){
		listarSensores.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	// 6. LISTAR SENSOR COM LEITURA
	@GetMapping("/com-leituras")
	public ResponseEntity<List<SensorComLeituraDTO>> listarComLeituras(){
		return ResponseEntity.ok(listarComLeituraSensor.executar());
	}
	
	// 7. ATUALIZAR LOCALIZAÇÃO 
	@PutMapping("/{id}/localizacao") // precisamos compor a rota com id
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> atualizarLocalizacao(
			@PathVariable String id, // esta annotation permite que um elemento variavel componha a rota
			@RequestBody AtualizarLocalizacaoDTO atualizarLoc){
		
		// acessando a DI para cadastrar o sensor
		atualizarLocalizacaoSensor.executar(id, atualizarLoc.localizacao());
		return ResponseEntity.ok().build();
	}
		
}
