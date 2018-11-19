package com.ibm.TreinamentoCambio.exception;

public class ObjetoContaVazia extends RuntimeException {

	  private Integer code = 1;

	    public ObjetoContaVazia(String message) {
	        super(message);
	    }

	    public Integer getCode() {
	        return code;
	    }
}
