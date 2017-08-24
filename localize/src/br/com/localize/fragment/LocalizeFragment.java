package br.com.localize.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.widget.Toast;


public class LocalizeFragment extends Fragment{

	private static final String INFORMAÇÃO = "Informação";
	
	public void addMensagem(final String titulo, final String mensagem) {

		new AlertDialog.Builder(getActivity()).setTitle(titulo).setMessage(mensagem).setNegativeButton("ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(final DialogInterface dialog, final int which) {

				dialog.cancel();

			}

		}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	public void addMensagem(final String mensagem) {

		this.addMensagem(INFORMAÇÃO, mensagem);
	}

	public void addMensagemToast(final String mensagem) {
		
		addMensagemToast(mensagem, Toast.LENGTH_SHORT);
	}

	public void addMensagemToast(final String mensagem, int duracao) {
		
		Toast toast = Toast.makeText(getActivity(), mensagem, duracao);

		toast.show();
	}
	
}
