package com.agro.sensores.domain.exception;

// classe para tratar potenciais erros de validação de dados 
public class ValidacaoException extends RuntimeException {
	
	/*
	  Essa linha é uma "especie" de "RG"(Registro Geral) da sua classe. 
	  Quando o Java precisa transformar um objeto em "bits" (para salvar num arquivo
	  ou enviar pela rede), ele "carimba" este numero no objeto - o identifica. 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * private static final: aqui, ao definir a propriedade, estamos garantindo que este 
	 * numero seja uma constante da classe e que "ninguem" possa altera-lo. 
	 * 
	 * long: é data type primitivo Java (um numero longo e inteiro)
	 * 
	 * serialVersionUID: é a nomenclatura-padrão que o Java procura para identificar a versão 
	 * da classe
	 * 
	 * 1L: é o numero da versão (o "L" indica que o valor é long)
	 * */
	
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
