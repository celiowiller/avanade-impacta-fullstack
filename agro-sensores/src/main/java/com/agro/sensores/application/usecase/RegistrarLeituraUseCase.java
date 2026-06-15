package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.models.Leitura;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.domain.strategy.ValidadorSensorStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrarLeituraUseCase {

	private final SensorRepository sensorRepo;
	private final LeituraRepository leituraRepo;
	private final SensorLocalizacaoRepository localizacaoRepo;
	private final List<ValidadorSensorStrategy> validadores;
	
	public void executar(
				String sensorId,
				Double valor,
				LocalDateTime dataHora
			) {
		
		// 1. definir uma var para receber como valor a busca pelo sensor
		var sensor = sensorRepo.buscarPorId(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));	
		
		// 2. definir uma nova var para receber como valor a buscar por sensor e data
		 var localizacaoHistorica = localizacaoRepo
	                .buscarPorSensorEData(sensorId, dataHora)
	                .orElseGet(() ->
	                    localizacaoRepo.buscarAtivaPorSensor(sensorId)
	                        .orElseThrow(() -> new RuntimeException("Sensor sem localização"))
	                );
		
		// 3. definir o objeto do domain Leitura - pacote de dados
		Leitura leitura = new Leitura(
				null,
				sensor,
				valor, 
				dataHora,
				localizacaoHistorica.getLocalizacao()
				);
		
		// 4. fazer uso dos validador strategy
		validadores.stream()
					.filter(v -> v.suportar(sensor.getTipo()))
					.forEach(v -> v.validar(leitura));
		
		// 5. agora, vamos salvar
		leituraRepo.salvar(leitura);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
