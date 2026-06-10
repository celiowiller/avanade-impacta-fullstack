package com.agro.sensores.infra.persistence.adapter;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.models.Leitura;
import com.agro.sensores.domain.models.Sensor;
import com.agro.sensores.infra.persistence.entity.LeituraEntity;
import com.agro.sensores.infra.persistence.entity.SensorEntity;
import com.agro.sensores.infra.persistence.repository.JpaSensorLocalizacaoRepository;
import com.agro.sensores.infra.persistence.repository.JpaSensorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LeituraRepositoryAdapter implements LeituraRepository {
	
	private final JpaSensorLocalizacaoRepository jpa;
	private final JpaSensorRepository sensorJpa;
	
	// ------------------------------------------------------
	
	// ------------------------------------------------------
	
	// =======================================
	//	ENTITY <-> DOMAIN
	// =======================================
	
	private Leitura toDomain(LeituraEntity entity) {
		// agora, precisamos definir uma variavel para fazer a busca
		// a partir da "injeção" sensorJpa para buscar o sensor
		SensorEntity se = sensorJpa.findById(
					entity.getSensor()
						.getId()).orElseThrow(
								 () -> new RuntimeException("Sensor não encontrado no DB!")
								);	
				// verificar se o nome do sensor é nulo
			if(se.getNome() == null) {
				throw new RuntimeException("Sensor sem nome no DB: " + se.getId());
			}
			
			Sensor sensor = new Sensor(
						se.getId(),
						se.getNome(),
						se.getLocalizacao(),
						se.isAtivo(),
						se.getTipo()
					);
			
			// agora, vamos definir a expressão de retorno do método como objeto do model Leitura
			return new Leitura(
						entity.getId(),
						sensor,
						entity.getValor(),
						entity.getDataHora(),
						entity.getLocalizacao()
					);
	}
	
	
	
	private LeituraEntity toEntity(Leitura d) {
		// 1ª etapa
		// definir o objeto entity para operar com o objeto de dominio
		LeituraEntity e = new LeituraEntity(); // aqui temos, uma representação "vazia"
		// da leitura no formato em que o DB entende
		
		
		// 2ª etapa: "pegamos" os dados do objeto de dominio e colocamos para o objeto entity
		// como se estivessemos preenchendo um formularo
		e.setId(d.getId());
		e.setValor(d.getValor());
		e.setDataHora(d.getDataHora());
		e.setLocalizacao(d.getLocalizacao());
		
		// 3ª etapa: aqui, validamos a existencia do sensor
		// pois fazer uma leitura sem um sensor seria algo totalmente "exotico"
		if(d.getSensor() == null || d.getSensor().getId() == null) {
			throw new RuntimeException("Sensor não pode ser nulo na leitura!");
		}
		
		// caso o contrario ocorra...
		// 4ª etapa: estamos tentando representar o relacionamento... 
		
		SensorEntity sensorRef = new SensorEntity();
		sensorRef.setId(d.getSensor().getId());
		e.setSensor(sensorRef);
		
		// conceitualmente: estamos dizendo que: não precisamos do sensor completo; precisamos 
		// saber, somente, qual é o sensor -> por isso fazemos referencia somente ao Id; 
		// portanto, as intruções acima indicam que determinada leitura pertence à um determinado
		// sensor com um id = x
		
		return e;
	}
	
	/*
	 *  acima, nossa implementação tenta responder a seguinte questão:
	 *  Como "transformo" um objeto de dominio - Leitura - num objeto que um DB
	 *  consegue armanezar - LeituraEntity?
	 * */
	
	
	
	
	
}
