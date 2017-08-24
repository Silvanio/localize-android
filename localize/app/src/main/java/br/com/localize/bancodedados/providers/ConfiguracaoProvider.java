package br.com.localize.bancodedados.providers;

import java.util.HashMap;

import br.com.localize.bancodedados.modelo.Configuracao;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * <p>
 * <b>Title:</b> ConfiguracaoProvider
 * </p>
 * 
 * <p>
 * <b>Description:</b> Classe responsável por compartilhar dados da tabela <code>tb_Configuracao</code>
 * </p>
 * 
 * <p>
 * <b>Company: </b> ITSS Factory
 * </p>
 * 
 * @author Silvânio Júnior
 * 
 * @version 1.0.0
 */
public class ConfiguracaoProvider extends SQLiteProvider {

	/** Atributo ID_PROVIDER. */
	public static final int ID_PROVIDER = 1;

	/** Atributo projectionsMap. */
	private HashMap<String, String> projectionsMap;

	/** Atributo uriMatcher. */
	private final UriMatcher matcher;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public ConfiguracaoProvider() {

		this.matcher = new UriMatcher(UriMatcher.NO_MATCH);

		this.matcher.addURI(this.getProviderName(), this.getSuffix(), ConfiguracaoProvider.ID_PROVIDER);
	}

	/**
	 * Método responsável por realizar consultar na base de dados compartilhada.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param uri
	 * 
	 * @param projection
	 * 
	 * @param selection
	 * 
	 * @param selectionArgs
	 * 
	 * @param sortOrder
	 * 
	 * @return <code>Cursor</code>
	 */
	@Override
	public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {

		final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		queryBuilder.setTables(Configuracao.TABLE);

		switch (this.matcher.match(uri)) {

			case ID_PROVIDER:

				queryBuilder.setProjectionMap(this.getProjectionsMap());

				break;

			default:

				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}

		final Cursor cursor = queryBuilder.query(this.getDatabase(), projection, selection, selectionArgs, null, null, sortOrder);

		cursor.setNotificationUri(this.getContext().getContentResolver(), uri);

		return cursor;
	}

	/**
	 * Método responsável por inserir registros na base de dados compartilhada.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param uri
	 * 
	 * @param values
	 * 
	 * @return <code>Uri</code>
	 */
	@Override
	public Uri insert(final Uri uri, final ContentValues values) {

		return this.insert(uri, values, Configuracao.TABLE);
	}

	/**
	 * Método responsável por alterar registros na base de dados compartilhada.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param uri
	 * 
	 * @param values
	 * 
	 * @param selection
	 * 
	 * @param selectionArgs
	 * 
	 * @return <code>int</code>
	 */
	@Override
	public int update(final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs) {

		int count = 0;

		switch (this.matcher.match(uri)) {

			case ID_PROVIDER:

				count = this.getDatabase().update(Configuracao.TABLE, values, selection, selectionArgs);

				break;

			default:

				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}

		this.getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	/**
	 * Método responsável por remover registros da base de dados compartilhada.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param uri
	 * 
	 * @param selection
	 * 
	 * @param selectionArgs
	 * 
	 * @return <code>int</code>
	 */
	@Override
	public int delete(final Uri uri, final String selection, final String[] selectionArgs) {

		int count = 0;

		switch (this.matcher.match(uri)) {

			case ID_PROVIDER:

				count = this.getDatabase().delete(Configuracao.TABLE, selection, selectionArgs);

				break;

			default:

				throw new IllegalArgumentException("URI desconhecida " + uri);
		}

		this.getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	/**
	 * Método responsável por retornar o tipo de acesso realizado.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param uri
	 * 
	 * @return <code>String</code>
	 */
	@Override
	public String getType(final Uri uri) {

		switch (this.matcher.match(uri)) {

			case ID_PROVIDER:

				return "vnd.android.cursor.dir/vnd.localize.configuracoes";

			default:

				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}
	}

	/**
	 * Método responsável por retornar a URI responsavel pela execução do provedor de conteúdo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>String</code>
	 */
	@Override
	protected String getProviderName() {

		return "br.com.localize.bancodedados.modelo.Configuracao";
	}

	/**
	 * Método responsável por retornar o sufixo da URL de acesso ao provedor de conteúdo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>String</code>
	 */
	@Override
	protected String getSuffix() {

		return "configuracoes";
	}

	/**
	 * Método responsável por retornar as colunas que serão projetadas da base de dados.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>HashMap</code>
	 */
	@Override
	public HashMap<String, String> getProjectionsMap() {

		if (this.projectionsMap == null) {

			this.projectionsMap = new HashMap<String, String>();
			this.projectionsMap.put(Configuracao.COLUMN_ID, Configuracao.COLUMN_ID);
			this.projectionsMap.put(Configuracao.COLUMN_KM, Configuracao.COLUMN_KM);
		}

		return this.projectionsMap;
	}
}
