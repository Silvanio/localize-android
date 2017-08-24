package br.com.localize.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import br.com.localize.R;

public class DetalheFornecedorActivity extends LocalizeActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		addActionBar();
		
		setContentView(R.layout.activity_detalhe_fornecedor);
		
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

}
