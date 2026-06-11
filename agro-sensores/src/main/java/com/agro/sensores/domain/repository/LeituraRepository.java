package com.agro.sensores.domain.repository;

import java.util.List;
import com.agro.sensores.domain.models.Leitura;



// esta interface NÃO É uma interface de repositorio do JPA
public interface LeituraRepository {
	Leitura salvar(Leitura leitura);
	
	List<Leitura> buscarPorSensor(String sensorId);

}
