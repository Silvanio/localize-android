package br.com.localize.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import br.com.localize.R;
import br.com.localize.adapter.AdapterItemFornecedor;
import br.com.localize.dto.FornecedorDTO;
import br.com.localize.util.GPSTracker;
import br.com.localize.util.UtilBitMap;
import br.com.localize.util.UtilString;
import br.com.localize.util.WsHelper;

/**
 * <p>
 * <b>Title:</b> Inicial.java
 * </p>
 * 
 * <p>
 * <b>Description:</b>Tela listagem de fornecedores.
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
public class Inicial extends LocalizeActivity {

	/** Atributo imagemPanel. */
	ImageView imagemPanel;

	/** Atributo fornecedorList. */
	ListView fornecedorList;

	/** Atributo adpItemFornecedor. */
	AdapterItemFornecedor adpItemFornecedor;

	/** Atributo txtDistancia. */
	EditText txtDistancia;

	/** Atributo distancia. */
	private Integer distancia;

	/** Atributo idTipoServico. */
	private Long idTipoServico;

	/** Atributo imgPanel. */
	private Bitmap imgPanel;

	private List<FornecedorDTO> listaFornecedores;

	/**
	 * Descrição Padrão: <br>
	 * Criar aplicativo <br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.addActionBar();

		this.setContentView(R.layout.inicial);

		this.fornecedorList = (ListView) this.findViewById(android.R.id.list);

		this.fornecedorList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				
				final Intent intent = new Intent(getApplicationContext(), DetalheFornecedorActivity.class);
				
				startActivity(intent);
			}
		});

        this.imagemPanel = (ImageView) this.findViewById(R.id.img_servico);

        Context context = imagemPanel.getContext();

        String icon = getIntent().getExtras().getString("imgPanel");

        imagemPanel.setImageResource(context.getResources().getIdentifier("drawable/"+icon, null, context.getPackageName()));

		this.listarFornecedores();

	}

	/**
	 * Método responsável por listar os fornecedores.
	 * 
	 * @author Silvânio Júnior
	 */
	private void listarFornecedores() {

		final GPSTracker gps = new GPSTracker(Inicial.this);

		if (gps.canGetLocation()) {

			this.distancia = this.getIntent().getIntExtra("distancia", 10);

			this.idTipoServico = this.getIntent().getLongExtra("idTipoServico", 0);

			final double latitude = gps.getLatitude();

			final double longitude = gps.getLongitude();

			Inicial.this.chamaObterDados(this.idTipoServico, latitude, longitude, this.distancia);

		} else {

			gps.showSettingsAlert();

		}
	}

	/**
	 * Método responsável por adicionar actionbar
	 * 
	 * @author Silvânio Júnior
	 */
	private void addActionBar() {

		this.getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 98, 0)));

		this.getActionBar().setIcon(R.drawable.logo_transparent);

		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		this.getActionBar().setHomeButtonEnabled(true);
	}

	/**
	 * Método responsável por fazer a chamada para obter os dados.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param idTipoServico
	 * 
	 * @param lat
	 * 
	 * @param lon
	 * 
	 * @param distancia
	 */
	private void chamaObterDados(final Long idTipoServico, final double lat, final double lon, final int distancia) {

		final ProgressDialog progressDialog = ProgressDialog.show(this, "Localizando", "Por favor, aguarde...");

		final AsyncTask<Void, Void, List<FornecedorDTO>> task = new AsyncTask<Void, Void, List<FornecedorDTO>>() {

			@Override
			protected List<FornecedorDTO> doInBackground(final Void... params) {

				final List<FornecedorDTO> fornecedores = WsHelper.obterDados(idTipoServico, lon, lat, distancia);

				Inicial.this.listaFornecedores = fornecedores;

				return fornecedores;
			}

			@Override
			protected void onPostExecute(final List<FornecedorDTO> result) {

				if (result != null) {

					Inicial.this.adpItemFornecedor = new AdapterItemFornecedor(Inicial.this, result);

					Inicial.this.fornecedorList.setAdapter(Inicial.this.adpItemFornecedor);

				}

				progressDialog.dismiss();

				super.onPostExecute(result);
			}

		};

		task.execute();
	}

	/**
	 * Descrição Padrão: <br>
	 * Create Menu.<br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		switch (item.getItemId()) {

			case android.R.id.home:

				super.onBackPressed();

				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Descrição Padrão: <br>
	 * Método responsável por criar o menu.<br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {

		this.getMenuInflater().inflate(R.menu.action_bar, menu);

		final MenuItem searchItem = menu.findItem(R.id.action_search);

		final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(final String query) {

				Inicial.this.filtrarFornecedores(query);

				return true;
			}

			@Override
			public boolean onQueryTextChange(final String query) {

				Inicial.this.filtrarFornecedores(query);

				return true;
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Método responsável por filtrar fornecedores.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param query
	 */
	private void filtrarFornecedores(final String query) {

		final List<FornecedorDTO> fornecedores = new ArrayList<FornecedorDTO>();

		for (final FornecedorDTO dto : this.listaFornecedores) {

			if (this.isNomeEncontrado(query, dto) || this.isDescricaoEncontrada(query, dto)) {

				fornecedores.add(dto);

			}

		}

		Inicial.this.adpItemFornecedor = new AdapterItemFornecedor(Inicial.this, fornecedores);

		Inicial.this.fornecedorList.setAdapter(Inicial.this.adpItemFornecedor);

	}

	/**
	 * Método responsável por verificar se a descrição está na pesquisa.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param query
	 * 
	 * @param dto
	 * 
	 * @return boolean
	 */
	private boolean isDescricaoEncontrada(final String query, final FornecedorDTO dto) {

		return this.isStringEncontrada(dto.getDescricao(), query);
	}

	/**
	 * Método responsável por verificar se a nome está na pesquisa.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param query
	 * 
	 * @param dto
	 * 
	 * @return boolean
	 */
	private boolean isNomeEncontrado(final String query, final FornecedorDTO dto) {

		return this.isStringEncontrada(dto.getNome(), query);
	}

	/**
	 * Método responsável por verificar se encontrou a string na pesquisa.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param valor
	 * 
	 * @param pesquisa
	 * 
	 * @return boolean
	 */
	private boolean isStringEncontrada(String valor, String pesquisa) {

		if (valor != null && !valor.isEmpty()) {

			valor = UtilString.normalizar(valor);

			pesquisa = UtilString.normalizar(pesquisa);

			return valor.contains(pesquisa);

		} else {

			return false;
		}

	}

}
