package br.com.localize.bancodedados.providers;

import java.util.HashMap;

import br.com.localize.bancodedados.modelo.Configuracao;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * <p>
 * <b>Title:</b> SQLiteProvider
 * </p>
 * 
 * <p>
 * <b>Description:</b> Provider de mais alto nível da aplicação.
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
public abstract class SQLiteProvider extends ContentProvider {

	/** Atributo database. */
	private SQLiteDatabase	database;

	/** Atributo dbHelper. */
	private DBHelper		dbHelper;

	/**
	 * Método responsável por iniciar o compartilhamento dos dados.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>boolean</code>
	 */
	@Override
	public boolean onCreate() {

		this.dbHelper = new DBHelper(this.getContext());

		this.database = this.dbHelper.getWritableDatabase();

		return Boolean.TRUE;
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
	 * @param table
	 * 
	 * @return <code>Uri</code>
	 */
	public Uri insert(final Uri uri, final ContentValues values, final String table) {

		final long row = this.database.insert(table, null, values);

		if (row > 0) {

			final Uri newUri = ContentUris.withAppendedId(this.getContentURI(), row);

			this.getContext().getContentResolver().notifyChange(newUri, null);

			return newUri;
		}

		throw new SQLException("Erro ao executar URI: " + uri);
	}

	/**
	 * Método responsável por retornar a URL de acesso ao provedor de conteúdo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>String</code>
	 */
	protected String getURL() {

		return "content://" + this.getProviderName() + "/" + this.getSuffix();
	}

	/**
	 * Método responsável por realizar o parse da URI criada.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>String</code>
	 */
	protected Uri getContentURI() {

		return Uri.parse(this.getURL());
	}

	/**
	 * Método responsável por retornar a URI responsavel pela execução do provedor de conteúdo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>String</code>
	 */
	protected abstract String getProviderName();

	/**
	 * Método responsável por retornar o sufixo da URL de acesso ao provedor de conteúdo.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @return <code>String</code>
	 */
	protected abstract String getSuffix();

	/**
	 * Retorna o valor do atributo <code>projectionsMap</code>
	 * 
	 * @return <code>HashMap<String,String></code>
	 */
	public abstract HashMap<String, String> getProjectionsMap();

	/**
	 * <p>
	 * <b>Title:</b> SQLiteHelper
	 * </p>
	 * 
	 * <p>
	 * <b>Description:</b> Classe responsável pela integração com a base de dados mantida no dispositivo.
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
	protected class DBHelper extends SQLiteOpenHelper {

		/** Atributo DATABASE_NAME. */
		private static final String	DATABASE_NAME					= "db-localize";

		/** Atributo DATABASE_VERSION. */
		private static final int	DATABASE_VERSION				= 1;

		private static final String	CREATE_TABLE_CONFIGURACAO	= "CREATE TABLE " + Configuracao.TABLE + "("

																				  + Configuracao.COLUMN_ID + " INTEGER PRIMARY KEY,"

																				  + Configuracao.COLUMN_KM + " TEXT" + ")";

		/**
		 * Responsável pela criação de novas instâncias desta classe.
		 * 
		 * @param context
		 */
		public DBHelper( final Context context ) {

			super(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
		}

		/**
		 * Método responsável por criar o esquema da base de dados mantida no dispositivo.
		 * 
		 * @author Silvânio Júnior
		 * 
		 * @param db
		 */
		@Override
		public void onCreate(final SQLiteDatabase db) {

			db.execSQL(DBHelper.CREATE_TABLE_CONFIGURACAO);
		}

		/**
		 * Método responsável por atualizar o esquema da base de dados mantida no dispositivo.
		 * 
		 * @author Silvânio Júnior
		 * 
		 * @param db
		 * 
		 * @param oldVersion
		 * 
		 * @param newVersion
		 */
		@Override
		public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS " + Configuracao.TABLE);

			this.onCreate(db);
		}
	}

	/**
	 * Retorna o valor do atributo <code>database</code>
	 * 
	 * @return <code>SQLiteDatabase</code>
	 */
	public SQLiteDatabase getDatabase() {

		return this.database;
	}

	/**
	 * Retorna o valor do atributo <code>dbHelper</code>
	 * 
	 * @return <code>SQLiteHelper</code>
	 */
	public DBHelper getDbHelper() {

		return this.dbHelper;
	}

	/**
	 * Define o valor do atributo <code>dbHelper</code>.
	 * 
	 * @param dbHelper
	 */
	public void setDbHelper(final DBHelper dbHelper) {

		this.dbHelper = dbHelper;
	}
}
