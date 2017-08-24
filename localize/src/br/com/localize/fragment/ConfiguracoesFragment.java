package br.com.localize.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import br.com.localize.R;
import br.com.localize.bancodedados.dao.ConfiguracaoDAO;
import br.com.localize.bancodedados.modelo.Configuracao;

/**
 * <p>
 * <b>Title:</b> ConfiguracoesFragment.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> Tela de configuração.
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
public class ConfiguracoesFragment extends LocalizeFragment {

	private final static String ARG = "menu_array";

	private final static int INTERVALO_DEFAULT = 10;

	private Configuracao configuracao;

	private int intervalo = 0;

	/**
	 * Descrição Padrão: <br>
	 * Criando a tela. <br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.fragment_configuracoes, container, false);

		final ConfiguracaoDAO dao = new ConfiguracaoDAO(rootView.getContext());

		this.configuracao = dao.obterConfiguracao();

		this.intervalo = Integer.parseInt(this.configuracao.getKm());

		this.inicializarSeekers(rootView);

		final int i = this.getArguments().getInt(ConfiguracoesFragment.ARG);

		final String configuracoes = this.getResources().getStringArray(R.array.menu_array)[i];

		this.getActivity().setTitle(configuracoes);

		final Button salvar = (Button) rootView.findViewById(R.id.btn_salvar);

		salvar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View view) {

				ConfiguracoesFragment.this.intervalo = ( (SeekBar) rootView.findViewById(R.id.seek_intervalo) ).getProgress();

				ConfiguracoesFragment.this.configuracao.setKm("" + ConfiguracoesFragment.this.intervalo);

				dao.salvar(ConfiguracoesFragment.this.configuracao);

				ConfiguracoesFragment.this.getActivity().onBackPressed();

				ConfiguracoesFragment.this.addMensagemToast("Configuração confirmada!");
			}

		});

		return rootView;
	}

	/**
	 * Método responsável por inicializar os <code>SeekBar</code>
	 * 
	 * @author Bruno Zafalão
	 */
	private void inicializarSeekers(final View rootView) {

		( (SeekBar) rootView.findViewById(R.id.seek_intervalo) ).setProgress(this.intervalo == 0 ? ConfiguracoesFragment.INTERVALO_DEFAULT : this.intervalo);

		this.interval(( (TextView) rootView.findViewById(R.id.txt_intervalo) ), this.intervalo == 0 ? ConfiguracoesFragment.INTERVALO_DEFAULT : this.intervalo);

		( (SeekBar) rootView.findViewById(R.id.seek_intervalo) ).setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(final SeekBar seek) {

				return;
			}

			@Override
			public void onStartTrackingTouch(final SeekBar seek) {

				return;
			}

			@Override
			public void onProgressChanged(final SeekBar seek, final int progress, final boolean arg2) {

				ConfiguracoesFragment.this.interval(( (TextView) rootView.findViewById(R.id.txt_intervalo) ), progress);
			}
		});
	}

	/**
	 * Método responsável por definir intervalo de sincronização.
	 * 
	 * @author Bruno Zafalão
	 * 
	 * @param seek
	 * 
	 * @param text
	 * 
	 * @param progress
	 */
	private void interval(final TextView text, final int progress) {

		text.setText(progress + " Km");

	}

}
