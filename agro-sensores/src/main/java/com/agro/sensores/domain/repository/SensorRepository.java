package com.agro.sensores.domain.repository;

import java.util.List;
import java.util.Optional;

import com.agro.sensores.domain.models.Sensor;

// o que nosso dominio precisa - em relação aos dados de sensor?
// o repositorio, na camada de dominio, descreve as operações que 
// devem ser executadas com os dados 
public interface SensorRepository {
	
	Sensor salvar(Sensor sensor);	
	Optional<Sensor> buscarPorId(String id);
	List<Sensor> buscarTodos();
	void deletar(String id);
}
