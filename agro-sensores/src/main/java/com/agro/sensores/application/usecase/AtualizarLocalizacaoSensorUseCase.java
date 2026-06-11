package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.models.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizarLocalizacaoSensorUseCase {
	
	private final SensorLocalizacaoRepository localizacaoRepo;
	private final SensorRepository sensorRepo;
	
	@Transactional
	public void executar(String sensorId, String novaLocalizacao) {
		
		// 1. buscar o registro para ser atualizado
		var sensor  = sensorRepo.buscarPorId(sensorId)
				.orElseThrow(() -> new RuntimeException("Sensor não encontrado!"));
		
		// 2. deifinir uma prop para recebe como valor o instante em que o sensor 
		// será alterado
		LocalDateTime agora = LocalDateTime.now();
		
		// 3. acessar a DI para fazer a busca ativa de sensor
		localizacaoRepo.buscarAtivaPorSensor(sensorId)
					.ifPresent(antigo -> {
						antigo.encerrar(agora);
						localizacaoRepo.salvar(antigo);
					});
		
		// 4. vamos definir uma nova prop para receber como valor uma nova localização
		SensorLocalizacao novaLoc = new SensorLocalizacao(
					null,
					sensorId,
					novaLocalizacao,
					agora,
					null
				);
		
		// 5. salvar a nova localização
		localizacaoRepo.salvar(novaLoc);
		
		
		// 6. ter a possibilidade de...salvar uma nova localização no DB
		sensor.alterarLocalizacao(novaLocalizacao);
		sensorRepo.salvar(sensor);
		
		
	}
}
