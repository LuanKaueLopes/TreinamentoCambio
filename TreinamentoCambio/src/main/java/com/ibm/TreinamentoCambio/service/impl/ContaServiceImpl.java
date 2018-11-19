package com.ibm.TreinamentoCambio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoCambio.model.Conta;
import com.ibm.TreinamentoCambio.repository.ContaRepository;
import com.ibm.TreinamentoCambio.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	private ContaRepository contaRepository;
	
	@Autowired
	public ContaServiceImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	@Override
	public Conta buscaConta(Long id) throws Exception {
		Optional<Conta> contaOptinal = contaRepository.findById(id);
		return contaOptinal.orElseThrow(()-> new Exception("Não foi possivel localizar o contato id: "+id ));
	}

	@Override
	public Conta criaConta(Conta conta) throws Exception {
		if(conta == null)
			throw new  Exception ("Conta vazia");
	    return	contaRepository.save(conta);
		
	}
	@Override
	public String changeCoin(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
