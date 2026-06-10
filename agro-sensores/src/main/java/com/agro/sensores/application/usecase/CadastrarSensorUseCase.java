package com.agro.sensores.application.usecase;

import org.springframework.stereotype.Service;

import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// aqui, não temos a annotation @Validated -> significa que esta validação de dados
// precisar ser estabelecida em algum outro contexto.
public class CadastrarSensorUseCase {

	private final SensorRepository sensorRepo;
	







}
