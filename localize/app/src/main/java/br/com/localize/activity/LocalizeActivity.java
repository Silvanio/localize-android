package br.com.localize.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * <p>
 * <b>Title:</b> LocalizeActivity.java
 * </p>
 * 
 * <p>
 * <b>Description:</b>Classe Activity do Localize.
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
public class LocalizeActivity extends Activity {

	/** Atributo INFORMAÇÃO. */
	private static final String INFORMACAO = "Informação";

	/**
	 * Método responsável por adicionar mensagem.
	 * 
	 * @author silvanioSilvânio Júnior
	 * 
	 * @param titulo
	 * 
	 * @param mensagem
	 */
	public void addMensagem(final String titulo, final String mensagem) {

		new AlertDialog.Builder(this).setTitle(titulo).setMessage(mensagem).setNegativeButton("ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {

				dialog.cancel();

			}
		}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	/**
	 * Método responsável por adicionar mensagem.
	 * 
	 * @author silvanioSilvânio Júnior
	 * 
	 * @param mensagem
	 */
	public void addMensagem(final String mensagem) {

		this.addMensagem(LocalizeActivity.INFORMACAO, mensagem);
	}

}
