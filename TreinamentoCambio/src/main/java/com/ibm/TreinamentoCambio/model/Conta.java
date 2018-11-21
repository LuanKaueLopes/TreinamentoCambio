package com.ibm.TreinamentoCambio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Conta {
	@Id
	@GeneratedValue(generator = "seq_conta", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, name = "seq_conta", sequenceName = "seq_conta")
	private Long id;
	private String nome;
	private String moeda;
	private Double value;
	private String agencia;
	private String conta;

}
