package com.agro.sensores.domain.strategy;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.exception.RegraNegocioException;
import com.agro.sensores.domain.models.Leitura;

/* o padrão strategy tem como premissa principal estabelecer processos de validação
 * para estrutura logicas que podem possuir comportamentos diversos
 * 
 * aqui, neste strategym vamos definir a validação de leitura para sensor tipo SOLO
 * 
 * criando, a partir daqui, um objeto - controlamos este objeto de forma precisa; esta classe se
 * tornará, então, um singleton -> uma unica instancia que precisará, posteriormente, somente
 * ser referenciada para que o objeto possa ser usado.
 * 
 * */

@Component // aqui, estamos dizendo: "crie e gerencie" essa classe, automaticamente -> significa 
// que não precisamos usar a palavra reservada - new - para este proposito; pois estamos usando
// a annotation @Component -> que é um Bean: um Bean é um objeto criado e gerenciado pelo 
// proprio Spring e fica disponivel para uso - quando necessario.
public class ValidadorSoloStrategy implements ValidadorSensorStrategy{
	
	// implementar o método suportar()
	public boolean suportar(TipoSensor tipo) {
		return tipo == TipoSensor.SOLO;
	}
	
	
	// implementar a validação da leitura 
	public void validar(Leitura leitura) {
		// validação de faixa de umidade
		if(leitura.getValor() < 0 || leitura.getValor() > 100) {
			// se a avaliação for considerada TRUE....
			throw new RegraNegocioException("Valor lido, de umidade do solo, invalido!");
		}	
		
	}
	
}
