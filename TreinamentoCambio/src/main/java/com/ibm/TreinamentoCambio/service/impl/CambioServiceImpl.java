package com.ibm.TreinamentoCambio.service.impl;

import org.springframework.stereotype.Service;

import com.ibm.TreinamentoCambio.service.CambioService;

@Service
public class CambioServiceImpl implements CambioService{

	public static final double DOLAR = 3.79;
	public static final double EURO = 4.27;
	
	public String fazCambioBrlToDolar(Long value) {
		return String.format("%.2f", value/CambioServiceImpl.DOLAR);
	}
	
	public String fazCambioBrToEuro  (Long value) {
		return String.format("%.2f", value/CambioServiceImpl.EURO);
	}

	public String fazCambioDolarToBrl(Long value) {
		return String.format("%.2f", value*CambioServiceImpl.DOLAR);
	}
	
	public String fazCambioDolarToEuro(Long value) {
		return String.format("%.2f", (value*CambioServiceImpl.DOLAR)/CambioServiceImpl.EURO); 
	}

	public String fazCambioEuroToBrl(Long value) {
		return String.format("%.2f", value*CambioServiceImpl.EURO);
	}

	public String fazCambioEuroToDolar(Long value) {
		return String.format("%.2f",(value*CambioServiceImpl.EURO)/CambioServiceImpl.DOLAR);
	}
	
	

}
