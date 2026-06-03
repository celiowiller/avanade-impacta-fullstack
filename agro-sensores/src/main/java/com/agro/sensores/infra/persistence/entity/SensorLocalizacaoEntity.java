package com.agro.sensores.infra.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.agro.sensores.domain.enums.TipoSensor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensores_localizacao")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "id")

// estamos montando uma table de historico
public class SensorLocalizacaoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@ManyToOne // um sensor pode ter muitas movimentações
	@JoinColumn(name = "sensor_id", nullable = false) // aqui, estamos criando a FK
	private SensorEntity sensor;
	
	@Column(nullable = false)
	private String localizacao;
	
	@Column(nullable = false)
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataFim;
	
}
