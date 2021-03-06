package com.ibm.TreinamentoCambio.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibm.TreinamentoCambio.controller.Resposta;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { ObjetoNaoEncontradoException.class })
	protected ResponseEntity<Object> handleObjetoNaoEcontratoException(RuntimeException ex, WebRequest request) {
		ObjetoNaoEncontradoException exception = (ObjetoNaoEncontradoException) ex;
		Resposta resposta = new Resposta(exception.getCode(), exception.getMessage(), null);
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = {ObjetoContaVazia.class})
	protected ResponseEntity<Object> handleObjetoContaVazia(RuntimeException ex, WebRequest request) {
		ObjetoNaoEncontradoException exception = (ObjetoNaoEncontradoException) ex;
		Resposta resposta = new Resposta(exception.getCode(), exception.getMessage(), null);
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}