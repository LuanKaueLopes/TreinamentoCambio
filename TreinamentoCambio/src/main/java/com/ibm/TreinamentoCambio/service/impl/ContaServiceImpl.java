package com.ibm.TreinamentoCambio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoCambio.exception.ObjetoContaVazia;
import com.ibm.TreinamentoCambio.exception.ObjetoNaoEncontradoException;
import com.ibm.TreinamentoCambio.model.Conta;
import com.ibm.TreinamentoCambio.repository.ContaRepository;
import com.ibm.TreinamentoCambio.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	private ContaRepository contaRepository;
	private CambioServiceImpl cambioService;

	@Autowired
	public ContaServiceImpl(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	@Override
	public Conta buscaConta(Long id)  {
		Optional<Conta> contaOptinal = contaRepository.findById(id);

		return contaOptinal
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Não foi possivel localizar o contato id: " + id));
	}

	@Override
	public Conta criaConta(Conta conta) {
		if (conta == null)
			throw new ObjetoContaVazia("Conta vazia");
		return contaRepository.save(conta);
	}


	@Override
	public Conta sacarConta(Long id, Double value) throws Exception {
		Optional<Conta> contaOptional = contaRepository.findById(id);
		Conta conta = contaOptional.get();
		conta.setValue(conta.getValue() - value);
		
		return contaRepository.save(conta);	
		}

	@Override
	public String changeCoin(Long id,String moeda) {
		Optional<Conta> contaOptinal = contaRepository.findById(id);
		Conta conta = contaOptinal.get();
		String ret = "";
		if(moeda.equals("BRL")) {
			if(conta.getMoeda().equals("USD")) {
				ret = cambioService.fazCambioDolarToBrl(new Long(conta.getValue().longValue()));
			}else
				ret = cambioService.fazCambioEuroToBrl(new Long(conta.getValue().longValue()));
		}
		
		conta.setValue(new Double(ret).doubleValue());
		contaRepository.save(conta);
		return conta.toString();
	}

	@Override
	public String deletarConta(Long id) {

		if (!contaRepository.existsById(id))
			throw new ObjetoNaoEncontradoException("Não foi encontrado nenhuma conta com esse id: " + id);

		contaRepository.deleteById(id);
		return "conta deletada";
	}

}
