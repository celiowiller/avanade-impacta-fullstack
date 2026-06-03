package com.agro.sensores.domain.strategy;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.models.Leitura;

// interface strategy para validação de sensores
public interface ValidadorSensorStrategy {
	// verificar se o validador suporta o tipo de sensor
	boolean suportar(TipoSensor tipo );
	
	// declarar o método para validação de leitura
	void validar(Leitura leitura);
}
