package com.agro.sensores.infra.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// classe de configuração de beans globais e reutilizaveis
@Configuration
public class BeansConfig {
	// este será nosso bean de Clock(permissão para controlar, se necessario, tempo de testes)
	@Bean
	Clock clock() {
		return Clock.systemDefaultZone();
	}
}
