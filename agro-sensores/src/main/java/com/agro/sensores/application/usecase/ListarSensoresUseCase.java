package com.agro.sensores.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.agro.sensores.api.dto.SensorResponseDTO;
import com.agro.sensores.domain.exception.RecursoNaoEncontradoException;
import com.agro.sensores.domain.models.Sensor;
import com.agro.sensores.domain.repository.LeituraRepository;
import com.agro.sensores.domain.repository.SensorLocalizacaoRepository;
import com.agro.sensores.domain.repository.SensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarSensoresUseCase {
	
	private final SensorRepository repo;
	private final SensorLocalizacaoRepository localizacaoRepo;
	private final LeituraRepository leituraRepo;
	
	public List<SensorResponseDTO> executar(){
		
		// neste passo, vamos definir a expressão de retorno do método
		return  repo.buscarTodos()
					.stream()
					.map(this::mapToResponse) // implementar posteriormente
					.collect(Collectors.toList());	
	
	 }
	
	// fora do método executar(), precisamos definir as ações que podem ser executadas
	// a partir da listagem dos sensores
	public SensorResponseDTO buscarPorId(String id){
		// vamos definir uma var que receberá como valor a busca pelo sensor indicado
		var sensor = repo.buscarPorId(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Sensor não encontrado!"));
		return mapToResponse(sensor); // implementar posteriormente
	}
	
	// agora, vamos implementar o método mapToResponse()
	
	private SensorResponseDTO mapToResponse(Sensor sensor) {
		// 1. buscar os dados reais de historico e telemetria
		var historicoDominio = localizacaoRepo.buscarTodosPorSensor(sensor.getId());
		var leiturasDominio = leituraRepo.buscarPorSensor(sensor.getId());
		
		// 2. "mapear" a lista de historico para o DTO
		List<SensorResponseDTO.LocalizacaoResponseDTO> historicoDTO = historicoDominio.stream()
				.map(
						h -> new SensorResponseDTO.LocalizacaoResponseDTO(
									h.getLocalizacao(),
									h.getDataInicio(),
									h.getDataFim()))
								.collect(Collectors.toList());
		
		// 3. "mapear" a lista de telemetria para o DTO
		List<SensorResponseDTO.TelemetriaResponseDTO> leiturasDTO = leiturasDominio.stream()
				.map(
						l -> new SensorResponseDTO.TelemetriaResponseDTO(
									l.getValor(),
									l.getDataHora()))
								.collect(Collectors.toList());
		
		// 4. definir a expressão de retorno do metodo - que nada mais é do que 
		// o objeto gerado do DTO que, agora, está "mapeado" pelas props
		
		return new SensorResponseDTO(
					sensor.getId(),
					sensor.getNome(),
					sensor.getLocalizacao(),					
					sensor.getTipo().name(),
					sensor.isAtivo(),
					historicoDTO,
					leiturasDTO
				);	
		
	}
	
	// para concluir as ações executadas na listagem de sensores, vamos definir 
	// o método para a exclusão de um registro
	public void deletar(String id) {
				repo.deletar(id);
			}
	
	
}
