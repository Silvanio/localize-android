package br.com.localize.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.localize.util.UtilBitMap;
import android.graphics.Bitmap;

/**
 * <p>
 * <b>Title:</b> FornecedorDTO.java
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
public class FornecedorDTO implements Serializable {

	/** Atributo serialVersionUID. */
	private static final long serialVersionUID = -1444166115428927564L;

	/** Atributo nome. */
	private String nome;

	/** Atributo telefone. */
	private String telefone;

	/** Atributo email. */
	private String email;

	/** Atributo longitude. */
	private Double longitude;

	/** Atributo latitude. */
	private Double latitude;

	/** Atributo endereco. */
	private String endereco;

	/** Atributo ditancia. */
	private Double ditancia;

	/** Atributo descricao. */
	private String descricao;
	
	/** Atributo icon. */
	private Bitmap icon;

	public static List<FornecedorDTO> buildList(String result) throws Exception {

		JSONArray jArray = new JSONArray(result);

		List<FornecedorDTO> forncedores = new ArrayList<FornecedorDTO>();

		for (int i = 0; i < jArray.length(); i++) {

			JSONObject jsonObject = new JSONObject(jArray.get(i).toString());

			FornecedorDTO fornecedor = new FornecedorDTO();

			fornecedor.setEmail(jsonObject.getString("email"));

			fornecedor.setNome(jsonObject.getString("nome"));

			fornecedor.setTelefone(jsonObject.getString("telefone"));

			fornecedor.setEndereco(jsonObject.getString("endereco"));

			fornecedor.setDitancia(jsonObject.getDouble("distancia"));
			
			fornecedor.setDescricao(jsonObject.getString("descricao"));
			
			String icon = jsonObject.getString("icon");

			fornecedor.setIcon(UtilBitMap.getBitmap(icon));

			forncedores.add(fornecedor);

		}

		return forncedores;
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public FornecedorDTO() {

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

	/**
	 * Retorna o valor do atributo <code>telefone</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getTelefone() {

		return this.telefone;
	}

	/**
	 * Define o valor do atributo <code>telefone</code>.
	 * 
	 * @param telefone
	 */
	public void setTelefone(final String telefone) {

		this.telefone = telefone;
	}

	/**
	 * Retorna o valor do atributo <code>email</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getEmail() {

		return this.email;
	}

	/**
	 * Define o valor do atributo <code>email</code>.
	 * 
	 * @param email
	 */
	public void setEmail(final String email) {

		this.email = email;
	}

	/**
	 * Retorna o valor do atributo <code>longitude</code>
	 * 
	 * @return <code>Double</code>
	 */
	public Double getLongitude() {

		return this.longitude;
	}

	/**
	 * Define o valor do atributo <code>longitude</code>.
	 * 
	 * @param longitude
	 */
	public void setLongitude(final Double longitude) {

		this.longitude = longitude;
	}

	/**
	 * Retorna o valor do atributo <code>latitude</code>
	 * 
	 * @return <code>Double</code>
	 */
	public Double getLatitude() {

		return this.latitude;
	}

	/**
	 * Define o valor do atributo <code>latitude</code>.
	 * 
	 * @param latitude
	 */
	public void setLatitude(final Double latitude) {

		this.latitude = latitude;
	}

	public String getEndereco() {

		return endereco;
	}

	public void setEndereco(String endereco) {

		this.endereco = endereco;
	}

	public Double getDitancia() {

		return ditancia;
	}

	public void setDitancia(Double ditancia) {

		this.ditancia = ditancia;
	}

	/**
	 * Retorna o valor do atributo <code>descricao</code>
	 * 
	 * @return <code>String</code>
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Define o valor do atributo <code>descricao</code>.
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

}
