package com.agro.sensores.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.agro.sensores.api.dto.CriarSensorDTO;
import com.agro.sensores.domain.models.Sensor;
import com.agro.sensores.domain.models.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Validated
// aqui, não temos a annotation @Validated -> significa que esta validação de dados
// precisar ser estabelecida em algum outro contexto.
public class CadastrarSensorUseCase {

	private final SensorRepository sensorRepo;
	private final SensorLocalizacaoRepository localizacaoRepo;
	
	@Transactional // a annotation @Transactional nos auxilia na definição
	// de uma "transação" de banco de dados!
	public Sensor executar(CriarSensorDTO dto) {
		
		// definir o objeto, com os dados de sensor, para serem cadastrados na base
		Sensor novoSensor = new Sensor(
					null,
					dto.nome(),
					dto.localizacao(),
					true,
					dto.tipo()					
				);
		
		// agora, uma vez que o objeto foi criado, precisamos salva-lo
		Sensor sensorSalvo = sensorRepo.salvar(novoSensor);
		
		// neste passo, é fundamental criarmos uma localização que seja 
		// naturalmente vinculada ao sensor que acaba de ser, tambem, criado
		SensorLocalizacao localizacao = new SensorLocalizacao(
					null,
					sensorSalvo.getId(),
					dto.localizacao(),
					LocalDateTime.now(),
					null
				);
		// precisamos, agora, salvar a localização
		localizacaoRepo.salvar(localizacao);
		
		// precisamos retornar o sensor salvo
		return sensorSalvo;
	}






}
