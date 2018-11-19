package com.ibm.TreinamentoCambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.TreinamentoCambio.service.CambioService;

@RestController
@RequestMapping("/cambio")
public class CambioController {

	private CambioService cambioService;

	@Autowired
	public CambioController(CambioService cambioService) {
		this.cambioService = cambioService;
	}

	@RequestMapping(value = "/BrToDolar/{value}", method = RequestMethod.GET)
	public String brlToDolar(@PathVariable Long value) {
		return "valor do BRL para dolar: " + cambioService.fazCambioBrlToDolar(value);
	}

	@RequestMapping(value = "/BrToEu/{value}", method = RequestMethod.GET)
	public String brToEu(@PathVariable Long value) {
		return "valor do BRL para euro: " + cambioService.fazCambioBrToEuro(value);
	}

	@RequestMapping(value = "/DolarToBr/{value}", method = RequestMethod.GET)
	public String dolarToBrl(@PathVariable Long value) {
		return "valor do dolar para BRL: " + cambioService.fazCambioDolarToBrl(value);
	}

	@RequestMapping(value = "/DolarToEuro/{value}", method = RequestMethod.GET)
	public String dolarToEuro(@PathVariable Long value) {
		return "valor do dollar para o euro :" + cambioService.fazCambioDolarToEuro(value);
	}
	
	@RequestMapping(value ="/EuroToBr/{value}",method =RequestMethod.GET)
	public String euroToBrl(@PathVariable Long value) {
		return "valor do euro para BRL: "+ cambioService.fazCambioEuroToBrl(value);
	}
	
	@RequestMapping(value ="/EuroToDolar/{value}",method =RequestMethod.GET)
	public String euroToDolar(@PathVariable Long value) {
		return "valor do euro para Dolar :"+ cambioService.fazCambioEuroToDolar(value);
	}

}
