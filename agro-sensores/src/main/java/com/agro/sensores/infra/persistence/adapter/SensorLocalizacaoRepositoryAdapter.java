package com.agro.sensores.infra.persistence.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.models.SensorLocalizacao;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.infra.persistence.entity.SensorLocalizacaoEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorLocalizacaoRepository;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SensorLocalizacaoRepositoryAdapter implements SensorLocalizacaoRepository {
	
	private final JpaSensorLocalizacaoRepository jpa;
	private final JpaSensorRepository sensorJpa;
	
	// -------------------------------------------------
	// 1. implementar o método salvar
	@Override
	public SensorLocalizacao salvar(SensorLocalizacao d) {
		return toDomain(jpa.save(toEntity(d)));
	}
	
	// 2. implementar o método para buscar os sensores ativos
	@Override
	public Optional<SensorLocalizacao> buscarAtivaPorSensor(String sensorId){
		return jpa.findFirstBySensor_IdDataInic(sensorId).map(this::toDomain);
	}
	
	// 3. implementar a busca por sensor e data
	@Override
	public Optional<SensorLocalizacao> buscarPorSensorEData(String sensorId, LocalDateTime data){
		return jpa.buscarPorSensorEData(sensorId, data).map(this::toDomain);
	}
	
	// 4. implementar a busca de todos os sensores
	@Override
	public List<SensorLocalizacao> buscarTodosPorSensor(String sensorId){
		return jpa.findAllBySensor_IdOrderDataInic(sensorId)
				.stream()
				.map(this::toDomain)
				.toList();
	}
	
	// --------------------------------------------------
	
	private SensorLocalizacao toDomain(SensorLocalizacaoEntity entity) {
		return new SensorLocalizacao(
					entity.getId(),
					entity.getSensor().getId(),
					entity.getLocalizacao(),
					entity.getDataInicio(),
					entity.getDataFim()
				);
	}
	
	private SensorLocalizacaoEntity toEntity(SensorLocalizacao d) {
		SensorLocalizacaoEntity e = new SensorLocalizacaoEntity();
		// acima, estamos criando um objeto a partir da classe SensorLocalizacaoEntity
		// porque o JPA só consegue salvar objetos entity -nunca objetos de dominio
		// mas nosso método propõe, exatamente, esta conversão
		// então, nosso objeto entity - e - vai "dar a possibilidade" do objeto de dominio
		// ser salvo/armazenado pela entity
		
		// este mapeamento deve ocorrer da seguinte forma:
		e.setId(d.getId());
		e.setLocalizacao(d.getLocalizacao());
		e.setDataInicio(d.getDataInicio());
		e.setDataFim(d.getDataFim());
		
		e.setSensor(
					// aqui, vamos fazer uso da segunda DI - injeção de dependencia
					sensorJpa.getReferenceById(d.getSensorId())
				);
		// agora, precisamos retorna nosso objeto
		return e;
	}
	
	
	
	
}
