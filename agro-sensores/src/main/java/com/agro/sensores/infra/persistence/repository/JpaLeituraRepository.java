package com.agro.sensores.infra.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.LeituraEntity;

public interface JpaLeituraRepository extends JpaRepository<LeituraEntity, Long>{
	// definir uma busca para todas as leituras do sensor
	List<LeituraEntity> findBySensor_IdOrderByDataHoraDesc(String sensorId);
}
