package com.ibm.TreinamentoCambio.util;

import com.ibm.TreinamentoCambio.model.Conta;
import com.ibm.TreinamentoCambio.service.impl.CambioServiceImpl;

public class CambioUtil {
	
	public double changeValue(Conta conta,String moeda,CambioServiceImpl cambioService) {
		Double value = null;
		String teste;
		
		if(moeda.equals("BRL") && moeda != null) {
			if(conta.getMoeda().equals("USD")) {
				teste =cambioService.fazCambioDolarToBrl(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				value = Double.parseDouble(teste);
			}if (conta.getMoeda().equals("EUR")) {
				teste = cambioService.fazCambioEuroToBrl(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				value = Double.parseDouble(teste);
			}
		}
		if(moeda.equals("USD") && moeda != null) {
			if(conta.getMoeda().equals("EUR")) {
				teste =cambioService.fazCambioDolarToEuro(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				value = Double.parseDouble(teste);
			}if(conta.getMoeda().equals("BRL")) {
				teste = cambioService.fazCambioBrlToDolar(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				value = Double.parseDouble(teste);
			}
		} 
		
		if(moeda.equals("EUR") && moeda != null) {
			if(conta.getMoeda().equals("USD")) {
				teste =cambioService.fazCambioEuroToDolar(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				value = Double.parseDouble(teste);
			}if(conta.getMoeda().equals("BRL")) {
				teste = cambioService.fazCambioEuroToBrl(conta.getValue().longValue());
				teste = teste.replaceAll(",",".");
				value = Double.parseDouble(teste);
			}
		}
		
		if(value == null)
			return 0;
		return value;
	}

}
