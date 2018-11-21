package com.ibm.TreinamentoCambio.service;

import com.ibm.TreinamentoCambio.model.Conta;

public interface ContaService {

	Conta buscaConta(Long id);

	Conta criaConta(Conta conta);

	String deletarConta(Long id);

	Conta depositaConta(Long id,Double value) throws Exception;

	Conta sacarConta(Long id, Double value,String moeda) throws Exception;

	String changeCoin(Long id,String moeda);
	
}
