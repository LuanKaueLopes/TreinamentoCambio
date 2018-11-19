package com.ibm.TreinamentoCambio.service;

public interface CambioService {
	 String fazCambioBrlToDolar(Long value);
	 String fazCambioBrToEuro(Long value); 
	 String fazCambioDolarToBrl(Long value);
	 String fazCambioDolarToEuro(Long value);
	 String fazCambioEuroToBrl(Long value);
	 String fazCambioEuroToDolar(Long value);
}
