package br.com.localize.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import br.com.localize.util.UtilBitMap;

/**
 * <p>
 * <b>Title:</b> TipoServicoDTO.java
 * </p>
 * 
 * <p>
 * <b>Description:</b>
 * </p>
 * 
 * <p>
 * <b>Company: </b> Fábrica de Software - Red & White IT Solution
 * </p>
 * 
 * @author Silvânio Júnior
 * 
 * @version 1.0.0
 */
public class TipoServicoDTO implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -6566410610033926901L;

	/** Atributo id */
	private Long id;

	/** Atributo nome. */
	private String nome;

	/** Atributo icon. */
	private Bitmap icon;

	/** Atributo panel. */
	private byte[] iconPanel;

	public static List<TipoServicoDTO> buildList(final String result) throws JSONException {

		final JSONArray jArray = new JSONArray(result);

		final List<TipoServicoDTO> tipoServicoList = new ArrayList<TipoServicoDTO>();

		for (int i = 0; i < jArray.length(); i++) {

			final JSONObject jsonObject = new JSONObject(jArray.get(i).toString());

			final TipoServicoDTO tipoServico = new TipoServicoDTO();

			tipoServico.setId(jsonObject.getLong("id"));

			tipoServico.setNome(jsonObject.getString("nome"));

			String iconTipoServico = jsonObject.getString("icon");

			String iconPanel = jsonObject.getString("panel");

			tipoServico.setIconPanel(UtilBitMap.getArray(iconPanel));

			tipoServico.setIcon(UtilBitMap.getBitmap(iconTipoServico));

			tipoServicoList.add(tipoServico);

		}

		return tipoServicoList;
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
	 * Retorna o valor do atributo <code>nome</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getNome() {

		return this.nome;
	}

	/**
	 * Define o valor do atributo <code>nome</code>.
	 * 
	 * @param nome
	 */
	public void setNome(final String nome) {

		this.nome = nome;
	}

	public Bitmap getIcon() {

		return icon;
	}

	public void setIcon(Bitmap icon) {

		this.icon = icon;
	}

	/**
	 * Retorna o valor do atributo <code>iconPanel</code>
	 * 
	 * @return <code>byte[]</code>
	 */
	public byte[] getIconPanel() {

		return iconPanel;
	}

	/**
	 * Define o valor do atributo <code>iconPanel</code>.
	 * 
	 * @param iconPanel
	 */
	public void setIconPanel(byte[] iconPanel) {

		this.iconPanel = iconPanel;
	}

}
