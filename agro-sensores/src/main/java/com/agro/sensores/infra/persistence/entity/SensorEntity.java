package com.agro.sensores.infra.persistence.entity;

import java.util.List;

import com.agro.sensores.domain.enums.TipoSensor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sensores")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SensorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String nome;
	
	private String localizacao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoSensor sensor;
	
	@Column(nullable = false)
	private boolean ativo;
	
	// ADICIONAR OS MAPEAMENTOS PARA PREVINIR O POTENCIAL ERRO 500
	
	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SensorLocalizacaoEntity> historico;
	
	/* @OneToMany:  aqui, estamos definindo uma relação -> a relação entre SensorEntity
	 * e SensorLocalizacaoEntity ;
	 * estamos dizendo que, a partir desta relação "temos um sensor com muitas localizações";
	 * 
	 * então, esta definição diz que: podemos ter um hitorico localização que pertence 
	 * ao sensor	 * 
	 * 
	 * orphanRemoval = true 
	 * */
	
	// Leitura é uma entidade "independente" do sensor; pois quem gerencia as leituras é
	// LeituraRepository
	@OneToMany(mappedBy = "sensor")
	private List<LeituraEntity> leituras;		
	
}
