package br.com.localize.fragment;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import br.com.localize.R;
import br.com.localize.activity.Inicial;
import br.com.localize.bancodedados.dao.ConfiguracaoDAO;
import br.com.localize.bancodedados.modelo.Configuracao;
import br.com.localize.dto.TipoServicoDTO;
import br.com.localize.util.WsHelper;

/**
 * <p>
 * <b>Title:</b> DesktopFragment.java
 * </p>
 * 
 * <p>
 * <b>Description:</b>Desktop Frament.
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
public class DesktopFragment extends LocalizeFragment {

	/** Atributo container. */
	private TableLayout container;

	private static final String ID_TIPO_SERVICO = "idTipoServico";

	private static final String NOME_TIPO_SERVICO = "nomeTipoServico";

	private static final String DISTANCIA = "distancia";

	private static final String IMG_PANEL = "imgPanel";

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.desktop, container, false);

		this.iniciarConfiguracoes(rootView);

		this.initDesktop(rootView);

		return rootView;
	}

	private void iniciarConfiguracoes(final View rootView) {

		final ConfiguracaoDAO dao = new ConfiguracaoDAO(rootView.getContext());

		Configuracao configuracao = dao.obterConfiguracao();

		if (configuracao == null) {

			configuracao = new Configuracao("10");

			dao.salvar(configuracao);

		}

	}

	/**
	 * Método responsável por inicializar os item do desktop.
	 * 
	 * @author silvanioSilvânio Júnior
	 * 
	 * @param rootView
	 */
	private void initDesktop(final View rootView) {

		this.container = (TableLayout) rootView.findViewById(R.id.container_menus);

		final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Localizando", "Por favor, aguarde...");
		
		final AsyncTask<Void, Void, List<TipoServicoDTO>> task = new AsyncTask<Void, Void, List<TipoServicoDTO>>() {

			@Override
			protected List<TipoServicoDTO> doInBackground(final Void... params) {

				final List<TipoServicoDTO> tipoServicoList = WsHelper.listarTipoServico();

				return tipoServicoList;
			}

			@Override
			protected void onPostExecute(final List<TipoServicoDTO> tipoServicoList) {

				int i = 0;

				TableRow row = null;

				for (final TipoServicoDTO tipoServico : tipoServicoList) {

					if (i == 0) {

						row = new TableRow(DesktopFragment.this.getActivity());

						row.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
				
					}

					final ImageView img = DesktopFragment.this.obterImagem(tipoServico);

					img.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
				
					row.addView(img);

					i++;

					if (i == 2) {

						i = 0;

						DesktopFragment.this.container.addView(row);
					}

				}

				progressDialog.hide();
			}

		};

		task.execute();
	}

	/**
	 * Método responsável por criar a imagem view.
	 * 
	 * @author silvanioSilvânio Júnior
	 * 
	 * @param tipoServico
	 * 
	 * @return <code>ImageButton</code>
	 */
	public ImageView obterImagem(final TipoServicoDTO tipoServico) {

		final ImageView imageView = new ImageView(this.getActivity());

		imageView.setClickable(true);

		imageView.setAdjustViewBounds(true);
		
		imageView.setPadding(5, 5, 5, 5);

		imageView.setScaleType(ScaleType.CENTER_CROP);

        Context context = imageView.getContext();

        imageView.setImageResource(context.getResources().getIdentifier("drawable/"+tipoServico.getIcon(), null, context.getPackageName()));

        imageView.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View view) {

				final ConfiguracaoDAO dao = new ConfiguracaoDAO(DesktopFragment.this.getActivity());

				final Integer intervalo = Integer.parseInt(dao.obterConfiguracao().getKm());

				final Intent intent = new Intent(DesktopFragment.this.getActivity(), Inicial.class);

				intent.putExtra(DesktopFragment.IMG_PANEL, tipoServico.getIconPanel());
				
				intent.putExtra(DesktopFragment.ID_TIPO_SERVICO, tipoServico.getId());

				intent.putExtra(DesktopFragment.NOME_TIPO_SERVICO, tipoServico.getNome());

				intent.putExtra(DesktopFragment.DISTANCIA, intervalo);

				DesktopFragment.this.startActivity(intent);
			}

		});

		return imageView;

	}

}
