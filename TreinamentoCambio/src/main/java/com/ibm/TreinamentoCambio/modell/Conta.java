package com.ibm.TreinamentoCambio.modell;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@GeneratedValue(
			generator="seq_conta",
			strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(
			allocationSize = 1,
			name="seq_conta",
			sequenceName = "seq_conta")
	private Long id;
	private int agencia;
	private int numeroConta;
	private String moeda;
	
}
