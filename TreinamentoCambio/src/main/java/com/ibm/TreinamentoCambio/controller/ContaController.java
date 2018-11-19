package com.ibm.TreinamentoCambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.TreinamentoCambio.model.Conta;
import com.ibm.TreinamentoCambio.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	private ContaService contaService;

	@Autowired
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String buscaConta(@PathVariable Long id) {
		return contaService.buscaConta(id) + "";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String criaConta(@RequestBody Conta conta) {
		return "" + contaService.criaConta(conta);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteConta(@PathVariable Long id) {
		return contaService.deletarConta(id);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public String sacarConta(@RequestBody Long id, double value) throws Exception {
		return ""+ contaService.sacarConta(id, value);
	}

}
