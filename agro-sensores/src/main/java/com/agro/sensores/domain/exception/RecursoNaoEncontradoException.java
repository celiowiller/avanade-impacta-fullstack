package com.agro.sensores.domain.exception;

// classe "tratará" a exceção, potencial, para algum recurso que, eventualmente, esteja 
// ausente
public class RecursoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}

/*
 *  quando temos o tratamento de exceptions via RuntimeException: temos 
 *  uma unchecked exception -> significa eu o compilador NÃO NOS OBRIGRARÁ O TRATAMENTO
 *  só usaremos se referenciarmos e se for, estritamente, necessario 
 * 
 */