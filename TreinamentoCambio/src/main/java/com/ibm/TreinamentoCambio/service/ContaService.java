package com.ibm.TreinamentoCambio.service;

import com.ibm.TreinamentoCambio.model.Conta;

public interface ContaService {

	Conta buscaConta(Long id);
	Conta criaConta(Conta conta);
	String changeCoin(int id);
	Conta sacarConta(Long id, Double value) throws Exception;
	
}
