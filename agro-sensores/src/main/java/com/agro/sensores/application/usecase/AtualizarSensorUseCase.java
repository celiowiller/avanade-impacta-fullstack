package com.agro.sensores.application.usecase;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.models.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizarSensorUseCase {
	
	private final SensorRepository repo;
	
	public void executar(String id, String nome) {
		
		Sensor sensor = repo.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado!"));
		
		// definir nosso objeto - eventualmente - atualizado
		Sensor atualizado = new Sensor(
				sensor.getId(),
				nome,
				sensor.getLocalizacao(),
				sensor.isAtivo(),
				sensor.getTipo()				
				);
		repo.salvar(sensor);
	}
}
