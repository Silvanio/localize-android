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
	private String icon;

	/** Atributo panel. */
	private String iconPanel;

	public static List<TipoServicoDTO> buildList(final String result) throws JSONException {

		final JSONArray jArray = new JSONArray(result);

		final List<TipoServicoDTO> tipoServicoList = new ArrayList<TipoServicoDTO>();

		for (int i = 0; i < jArray.length(); i++) {

			final JSONObject jsonObject = new JSONObject(jArray.get(i).toString());

			final TipoServicoDTO tipoServico = new TipoServicoDTO();

			tipoServico.setId(jsonObject.getLong("id"));

			tipoServico.setNome(jsonObject.getString("nome"));

			String iconTipoServico = jsonObject.getString("identificadorImagem");

			String iconPanel = jsonObject.getString("identificadorPainel");

			tipoServico.setIconPanel(iconPanel);

			tipoServico.setIcon(iconTipoServico);

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconPanel() {
        return iconPanel;
    }

    public void setIconPanel(String iconPanel) {
        this.iconPanel = iconPanel;
    }
}
