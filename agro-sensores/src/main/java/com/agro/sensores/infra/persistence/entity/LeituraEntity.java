package com.agro.sensores.infra.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
/*import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;*/

@Entity
@Table(name = "leituras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeituraEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Relacionamento com sensor(muitos para um)
	@ManyToOne(optional = false) // significa que o relacionamento é MANDATÓRIO!
	// o "atributo" optinal = false PERTENCE À QUEM? R.: PERTENCE A ANNOTATION
	// mas está "conversando" com o Java core
	
	@JoinColumn(name = "sensor_id", nullable = false)// aqui, nullable = false, pertence a 
	// annotation - @JoinColumn
	// mas está "conversando" com o DB
	private SensorEntity sensor;
	
	// valor da leitura
	@Column(nullable = false)
	private Double valor;
	
	// data e hora
	@Column(nullable = false)
	private LocalDateTime dataHora;
	
	// localização
	@Column(nullable = false)
	private String localizacao;
	
	
}
