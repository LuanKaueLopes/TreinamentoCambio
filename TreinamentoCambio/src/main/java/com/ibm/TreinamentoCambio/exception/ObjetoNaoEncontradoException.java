package com.ibm.TreinamentoCambio.exception;

public class ObjetoNaoEncontradoException extends RuntimeException{
	private Integer code =1;
	
	public ObjetoNaoEncontradoException(String message) {
		super(message);
	}
	
	public Integer getCode() {
		return code;
	}
}
