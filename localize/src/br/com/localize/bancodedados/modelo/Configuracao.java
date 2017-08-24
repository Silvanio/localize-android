package br.com.localize.bancodedados.modelo;

/**
 * <p>
 * <b>Title:</b> Atualizacao
 * </p>
 * 
 * <p>
 * <b>Description:</b> Entidade <code>Atualizacao</code>
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
public class Configuracao {

	/** Atributo id. */
	private Long id;

	/** Atributo data. */
	private String km;

	/** Atributo TABLE. */
	public static final String TABLE = "tb_configuracao";

	/** Atributo COLUMN_ID. */
	public static final String COLUMN_ID = "id_configuracao";

	/** Atributo COLUMN_DATA. */
	public static final String COLUMN_KM = "km";

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param data
	 * 
	 * @param hora
	 */
	public Configuracao( final String km ) {

		this.km = km;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param data
	 * 
	 * @param hora
	 */
	public Configuracao(final Long id, final String km ) {
		
		this.id = id;
		
		this.km = km;
	}

	/**
	 * Retorna o valor do atributo <code>id</code>
	 * 
	 * @return <code>Long</code>
	 */
	public Long getId() {

		return this.id;
	}

	/**
	 * Define o valor do atributo <code>id</code>.
	 * 
	 * @param id
	 */
	public void setId(final Long id) {

		this.id = id;
	}

	/**
	 * Retorna o valor do atributo <code>km</code>
	 *
	 * @return <code>String</code>
	 */
	public String getKm() {

		return this.km;
	}

	/**
	 * Define o valor do atributo <code>km</code>.
	 *
	 * @param km
	 */
	public void setKm(final String km) {

		this.km = km;
	}

}
