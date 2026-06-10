package com.agro.sensores.infra.persistence.adapter;

//import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.models.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor // esta annotation gera, automaticamente, um construtor contendo 
// todos os campos/fields que final
public class SensorRepositoryAdapter implements SensorRepository {
	
	private final JpaSensorRepository jpa;
	
	// 1. salvar o sensor
	@Override // aqui, a annotation deixa explicito a sobrescrita do metodo 
	// declarado na interface 
	public Sensor salvar(Sensor sensor) {
		SensorEntity savedEntity = jpa.save(toEntity(sensor));
		
		// aqui, estamos retornando o sensor que foi salvo
		return toDomain(savedEntity);
	}
	/*
	 a implementação acima, a persistencia/armazenamento do registro e seu retorno, 
	 chamamos de persistencia de dominio; esta persistencia é responsavel por "traduzir"
	 um objeto de dominio(Sensor) para um objeto de infra(SensorEntity), então, dessa forma
	 persistimos/armazenamos o registro;
	 na sequencia, "devolvemos" ao dominio a versão persistida/armazenada e atualizada do objeto -
	 assim, podemos exibi-lo/manipula-lo conforme necessario.
	*/
	
	// 2. implementar a busca por id
	@Override
	public Optional<Sensor> buscarPorId(String id){
		return jpa.findById(id).map(this::toDomain);
	}
	
	// 3. implementar as busca para todos os sensores
	@Override
	public List<Sensor> buscarTodos(){
		return jpa.findAll()
				.stream()
				.map(this::toDomain)
				.collect(Collectors.toList());
	}
	
	// 4. implementar a exclusão de um sensor - devidamente identificado
	@Override
	public void deletar(String id) {
		jpa.deleteById(id);
	}
	
	// ------------------------------------------------------
	private Sensor toDomain(SensorEntity entity) {
		return new Sensor(
					entity.getId(),
					entity.getNome(),
					entity.getLocalizacao(),
					entity.isAtivo(),
					entity.getTipo()
					
				);
			}
	private SensorEntity toEntity(Sensor sensor) {
		return new SensorEntity(
					sensor.getId(),
					sensor.getNome(),
					sensor.getLocalizacao(),					
					sensor.isAtivo(),
					sensor.getTipo(),
					null, // campo 'historico' 
					null  // campo 'leituras'
				);
		}
}
