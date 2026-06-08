package com.agro.sensores.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agro.sensores.infra.persistence.entity.SensorEntity;

public interface JpaSensorRepository extends JpaRepository<SensorEntity, String> {
	/*
	 * ao praticar a "extensão" com JpaRepository temos a possibilidade de fazer uso 
	 * de uma série de métodos para estabelcer fluxo de dados 
	 * por exemplos:
	 * 
	 * save()
	 * findById()
	 * findAll()
	 * deleteById()
	 * count()
	 * entre outros...
	 * */
}
