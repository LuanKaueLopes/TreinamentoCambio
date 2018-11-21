package com.ibm.TreinamentoCambio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoCambio.exception.ObjetoContaVazia;
import com.ibm.TreinamentoCambio.exception.ObjetoNaoEncontradoException;
import com.ibm.TreinamentoCambio.model.Conta;
import com.ibm.TreinamentoCambio.repository.ContaRepository;
import com.ibm.TreinamentoCambio.service.ContaService;
import com.ibm.TreinamentoCambio.util.CambioUtil;

@Service
public class ContaServiceImpl implements ContaService {

	private ContaRepository contaRepository;
	private CambioServiceImpl cambioService = new CambioServiceImpl();
	CambioUtil cambioUtil = new CambioUtil();

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
	public Conta sacarConta(Long id, Double value,String moeda) throws Exception {
		Optional<Conta> contaOptional = contaRepository.findById(id);
		Conta conta = contaOptional.get();
		String moedaOriginal = conta.getMoeda();
		Double d=cambioUtil.changeValue(conta, moeda, cambioService);
		if (d ==0) {
			conta.setValue(conta.getValue() - value);
			return contaRepository.save(conta);
		}else if (d !=0) {
			conta.setValue(d - value);
			conta.setMoeda(moeda);
		}
		    
		d=cambioUtil.changeValue(conta, moedaOriginal, cambioService);
		conta.setValue(d);
		conta.setMoeda(moedaOriginal);
		return contaRepository.save(conta);	
		}

	@Override
	public String changeCoin(Long id,String moeda) {
		Optional<Conta> contaOptinal = contaRepository.findById(id);
		Conta conta = contaOptinal.get();
		Double d=cambioUtil.changeValue(conta, moeda, cambioService);
		if(d==0)
			return "moeda no mesmo valor da conta";
		conta.setMoeda(moeda);
		conta.setValue(d);
		return contaRepository.save(conta).toString();
	}
	
	@Override
	public Conta depositaConta(Long id, Double value) throws Exception {
		Optional <Conta> contaOptional = contaRepository.findById(id);
		Conta conta = contaOptional.get();
		if(conta.getValue()==null)
			conta.setValue(value);
		else
		conta.setValue(conta.getValue() + value);
		
		return contaRepository.save(conta);
		
	}

	@Override
	public String deletarConta(Long id) {

		if (!contaRepository.existsById(id))
			throw new ObjetoNaoEncontradoException("Não foi encontrado nenhuma conta com esse id: " + id);

		contaRepository.deleteById(id);
		return "conta deletada";
	}

}
