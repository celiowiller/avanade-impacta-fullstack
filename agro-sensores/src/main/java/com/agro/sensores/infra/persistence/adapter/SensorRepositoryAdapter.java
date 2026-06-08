package com.agro.sensores.infra.persistence.adapter;

import com.agro.sensores.domain.models.Sensor;
import com.agro.sensores.domain.repository.SensorRepository;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

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
	 a implçementação acima, a persistencia/armazenamento do registro e seu retorno, 
	 chamamos de persistencia de dominio; esta persistencia é responsavel por "traduzir"
	 um objeto de dominio(Sensor) para um objeto de infra(SensorEntity), então, dessa forma
	 persistimos/armazenamos o registro;
	 na sequencia, "devolvemos" ao dominio a versão persistida/armazenada e atualizada do objeto -
	 assim, podemos exibi-lo/manipula-lo conforme necessario.
	*/
	
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
