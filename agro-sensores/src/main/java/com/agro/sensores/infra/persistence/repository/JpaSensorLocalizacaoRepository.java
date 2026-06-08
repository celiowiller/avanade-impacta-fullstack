package com.agro.sensores.infra.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agro.sensores.infra.persistence.entity.SensorLocalizacaoEntity;

public interface JpaSensorLocalizacaoRepository extends JpaRepository<SensorLocalizacaoEntity, 
String> {
	// definir um  método custom para recuperar um sensor pela data de 
	// inicio e fim de um determinada localização
	Optional<SensorLocalizacaoEntity>  findFirstBySensor_IdDataInic(String sensorId);
	
	// aqui, o recurso Optional<T> é utilizado para evitar o famigerado 
	// NUllPointerException
	
	// aqui, abaixo, a annotation permite que possamos escrever uma consulta 
	// explicitamente, em vez de deixar o Spring Data JPA gera-la - estamos usando o JPQL
	//
	@Query(""" 
			SELECT s FROM SensorLocalizacaoEntity s
			WHERE s.sensor.id = :sensorId
			AND s.dataInicio <= :data
			AND (s.dataFim IS NULL OR s.dataFim >= :data)
			ORDER BY s.dataInicio Desc
			""")
	Optional<SensorLocalizacaoEntity> buscarPorSensorEData(
			@Param("sensorId") String sensorId,
			@Param("data") LocalDateTime data
			);
	
	// acima, temos a annotation @Param -> que seu objetivo e indicar para a @Query aquilo que, 
	// exatamente, deve ser executado!
	
	// vamos listar as localizações 
	List<SensorLocalizacaoEntity> findAllBySensor_IdOrderDataInic(String sensorId);	

}

/*
 *  historico de localização simples seria: 
 *  Sensor A -> Local X
 *  
 *  queremos, na verdade, uma listagem dessa forma:
 *  Sensor A -> Local X -> de 01/02 até 30/04
 *  Sensor A -> Local Z -> de 01/05 até 02/05  11:31 até 11:45
 *  Portanto, a nossa entity não representará somente uma localização; mas representará
 *  uma vigência/historico temporal de uma localização  
 * 
 * */





