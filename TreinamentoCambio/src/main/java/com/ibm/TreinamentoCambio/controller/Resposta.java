package com.ibm.TreinamentoCambio.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resposta {
	int code;
	private String error;
	private Object body;
}
