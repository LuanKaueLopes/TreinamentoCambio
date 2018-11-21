package com.ibm.TreinamentoCambio.service.impl;

import java.util.Optional;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.TreinamentoCambio.exception.ObjetoContaVazia;
import com.ibm.TreinamentoCambio.exception.ObjetoNaoEncontradoException;
import com.ibm.TreinamentoCambio.model.Conta;
import com.ibm.TreinamentoCambio.repository.ContaRepository;
import com.ibm.TreinamentoCambio.service.ContaService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class ContaServiceImpl implements ContaService {

	private ContaRepository contaRepository;
	private CambioServiceImpl cambioService = new CambioServiceImpl();

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
		Double d=null;
		String teste;
		Optional<Conta> contaOptinal = contaRepository.findById(id);
		Conta conta = contaOptinal.get();
		if(moeda.equals("BRL") && moeda != null) {
			if(conta.getMoeda().equals("USD")) {
				teste =cambioService.fazCambioDolarToBrl(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				d = Double.parseDouble(teste);
			}if (conta.getMoeda().equals("EUR")) {
				teste = cambioService.fazCambioEuroToBrl(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				d = Double.parseDouble(teste);
			}
		}
		if(moeda.equals("USD") && moeda != null) {
			if(conta.getMoeda().equals("EUR")) {
				teste =cambioService.fazCambioDolarToEuro(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				d = Double.parseDouble(teste);
			}if(conta.getMoeda().equals("BRL")) {
				teste = cambioService.fazCambioBrlToDolar(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				d = Double.parseDouble(teste);
			}
		} 
		
		if(moeda.equals("EUR") && moeda != null) {
			if(conta.getMoeda().equals("USD")) {
				teste =cambioService.fazCambioEuroToDolar(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				d = Double.parseDouble(teste);
			}if(conta.getMoeda().equals("BRL")) {
				teste = cambioService.fazCambioEuroToBrl(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				d = Double.parseDouble(teste);
			}
		}
		if(d == null) {
			return "moeda passada é a mesma da conta aque abrc";
		}
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
