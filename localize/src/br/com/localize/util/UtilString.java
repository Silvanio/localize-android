package br.com.localize.util;

import java.text.Normalizer;

/**
 * <p>
 * <b>Title:</b> UtilString.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe utilitaria de string.
 * </p>
 * 
 * <p>
 * <b>Company: </b> Fábrica de Software - Localize
 * </p>
 * 
 * @author Silvânio Júnior
 * 
 * @version 1.0.0
 */
public class UtilString {

	private static final String EMPTY = "";

	private static final String EXPRESSION = "[^\\p{ASCII}]";

	/**
	 * Método responsável por retirar acentos, colocar em caixa alta e retirar espaços.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param valor
	 * 
	 * @return String
	 */
	public static String normalizar(String valor) {

		if (valor != null && !valor.isEmpty()) {

			valor = Normalizer.normalize(valor.toUpperCase().trim(), Normalizer.Form.NFD).replaceAll(UtilString.EXPRESSION, UtilString.EMPTY);

		}

		return valor;
	}

}
