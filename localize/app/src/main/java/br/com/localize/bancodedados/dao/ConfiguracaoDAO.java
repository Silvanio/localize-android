package br.com.localize.bancodedados.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.localize.bancodedados.modelo.Configuracao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ConfiguracaoDAO {

	/** Atributo context. */
	private Context context;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param context
	 */
	public ConfiguracaoDAO( final Context context ) {

		this.context = context;
	}

	public Configuracao obterConfiguracao() {

		final Uri configs = Uri.parse("content://br.com.localize.bancodedados.modelo.Configuracao/configuracoes");

		final Cursor result = this.getContext().getContentResolver().query(configs, null, null, null, null);

		final List<Configuracao> configuracoes = new ArrayList<Configuracao>();

		if (result.moveToFirst()) {

			do {

				final Configuracao configuracao = new Configuracao(result.getLong(result.getColumnIndex(Configuracao.COLUMN_ID)),result.getString(result.getColumnIndex(Configuracao.COLUMN_KM)));

				configuracoes.add(configuracao);

			} while (result.moveToNext());
		}

		result.close();

		return configuracoes != null && configuracoes.size() > 0 ? configuracoes.get(0) : null;
	}
	

	public void salvar(final Configuracao configuracao) {

		final Uri configs = Uri.parse("content://br.com.localize.bancodedados.modelo.Configuracao/configuracoes");

		final ContentValues values = new ContentValues();

		values.put(Configuracao.COLUMN_KM, configuracao.getKm());

		getContext().getContentResolver().delete(configs, null, null);

		getContext().getContentResolver().insert(configs, values);

	}

	/**
	 * Retorna o valor do atributo <code>context</code>
	 *
	 * @return <code>Context</code>
	 */
	public Context getContext() {

		return this.context;
	}

	/**
	 * Define o valor do atributo <code>context</code>.
	 *
	 * @param context
	 */
	public void setContext(final Context context) {

		this.context = context;
	}

}
