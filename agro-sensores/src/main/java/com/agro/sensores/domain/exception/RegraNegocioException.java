package com.agro.sensores.domain.exception;

// classe que "tratará" exceções potenciais para regras de negocio
public class RegraNegocioException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RegraNegocioException(String mensagem) {
		super(mensagem);
	}
}
