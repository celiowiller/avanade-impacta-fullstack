package com.agro.sensores.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.api.dto.SensorComLeituraDTO;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarSensoresComLeituraUseCase {
	
	private final SensorRepository sensorRepo;
	private final LeituraRepository leituraRepo;
	private final SensorLocalizacaoRepository localizacaoRepo;
	
	
	public List<SensorComLeituraDTO> executar(){
		
		return sensorRepo.buscarTodos()
				.stream()
				.map(
						sensor -> {
							// 1. Buscar as leituras
							var leituras = leituraRepo.buscarPorSensor(sensor.getId());
							
							// 2. "montar" o historico das leituras
							var historico = localizacaoRepo.buscarTodosPorSensor(sensor.getId());
							
							// 3. "montar" o DTO com os parametros, devidamente, tipados
							return new SensorComLeituraDTO(
									sensor.getId(),
									sensor.getNome(),
									sensor.getLocalizacao(),
									leituras,
									historico
									);
							
						}).toList();
		
	
	}
}
