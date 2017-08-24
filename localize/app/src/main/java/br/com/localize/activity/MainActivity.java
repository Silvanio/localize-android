package br.com.localize.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.localize.R;
import br.com.localize.fragment.ConfiguracoesFragment;
import br.com.localize.fragment.DesktopFragment;

/**
 * <p>
 * <b>Title:</b> MainActivity.java
 * </p>
 * 
 * <p>
 * <b>Description:</b> MainActivity
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
public class MainActivity extends LocalizeActivity {

	/** Atributo MENU_ARRAY. */
	private static final String MENU_ARRAY = "menu_array";

	/** Atributo mDrawerLayout. */
	private DrawerLayout mDrawerLayout;

	/** Atributo mDrawerList. */
	private ListView mDrawerList;

	/** Atributo mDrawerToggle. */
	private ActionBarDrawerToggle mDrawerToggle;

	/** Atributo mDrawerTitle. */
	private CharSequence mDrawerTitle;

	/** Atributo mTitle. */
	private CharSequence mTitle;

	/** Atributo mConfiguracoesMenuTittle. */
	private String[] mConfiguracoesMenuTittle;

	/** Atributo fragment. */
	private Fragment fragment;

	/**
	 * Descrição Padrão: <br>
	 * criando o actibity <br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_main);

		this.mTitle = this.mDrawerTitle = this.getTitle();

		this.mConfiguracoesMenuTittle = this.getResources().getStringArray(R.array.menu_array);

		this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);

		this.mDrawerList = (ListView) this.findViewById(R.id.left_drawer);

		this.mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		this.mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, this.mConfiguracoesMenuTittle));

		this.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		this.getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 98, 0)));

		this.getActionBar().setIcon(R.drawable.logo_transparent);

		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		this.getActionBar().setHomeButtonEnabled(true);

		this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

			@Override
			public void onDrawerClosed(final View view) {

				MainActivity.this.getActionBar().setTitle(MainActivity.this.mTitle);

				MainActivity.this.invalidateOptionsMenu();

			}

			@Override
			public void onDrawerOpened(final View drawerView) {

				MainActivity.this.getActionBar().setTitle(MainActivity.this.mDrawerTitle);

				MainActivity.this.invalidateOptionsMenu();

			}
		};

		this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);

		if (savedInstanceState == null) {

			this.abrirDesktop(0);

		}

	}

	/**
	 * Descrição Padrão: <br>
	 * Método responsável por criar os itens.<br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		if (this.mDrawerToggle.onOptionsItemSelected(item)) {

			return true;

		}

		return super.onOptionsItemSelected(item);
	}


	/**
	 * Método responsável por selecionar os itens.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param position
	 */
	private void selectItem(final int position) {

		switch (position) {
			case 0:
				this.abrirDesktop(position);

				break;
			case 1:
				this.abrirConfiguracoes(position);

				break;

			case 2:

				this.finish();

				break;

			default:
				break;
		}

	}

	/**
	 * Método responsável por abrir a tela de desktop.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param position
	 */
	private void abrirDesktop(final int position) {

		this.fragment = new DesktopFragment();

		final Bundle args = new Bundle();

		args.putInt(MainActivity.MENU_ARRAY, position);

		final FragmentManager fragmentManager = this.getFragmentManager();

		fragmentManager.beginTransaction().replace(R.id.content_frame, this.fragment).commit();

		this.mDrawerList.setItemChecked(position, true);

		this.setTitle(this.mConfiguracoesMenuTittle[position]);

		this.mDrawerLayout.closeDrawer(this.mDrawerList);

	}

	/**
	 * Método responsável por abrir as telas de configurações.
	 * 
	 * @author Silvânio Júnior
	 * 
	 * @param position
	 */
	private void abrirConfiguracoes(final int position) {

		this.fragment = new ConfiguracoesFragment();

		final Bundle args = new Bundle();

		args.putInt(MainActivity.MENU_ARRAY, position);

		this.fragment.setArguments(args);

		final FragmentManager fragmentManager = this.getFragmentManager();

		fragmentManager.beginTransaction().replace(R.id.content_frame, this.fragment).commit();

		this.mDrawerList.setItemChecked(position, true);

		this.setTitle(this.mConfiguracoesMenuTittle[position]);

		this.mDrawerLayout.closeDrawer(this.mDrawerList);
	}

	/**
	 * Descrição Padrão: <br>
	 * Método responsável por informar o titulo.<br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#setTitle(java.lang.CharSequence)
	 */
	@Override
	public void setTitle(final CharSequence title) {

		this.mTitle = title;

		this.getActionBar().setTitle(this.mTitle);

	}

	/**
	 * Descrição Padrão: <br>
	 * Método chamado após criar a tela.<br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(final Bundle savedInstanceState) {

		super.onPostCreate(savedInstanceState);

		this.mDrawerToggle.syncState();

	}

	/**
	 * Descrição Padrão: <br>
	 * Método responsável por criar as configurações. <br>
	 * 
	 * {@inheritDoc}
	 * 
	 * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(final Configuration newConfig) {

		super.onConfigurationChanged(newConfig);

		this.mDrawerToggle.onConfigurationChanged(newConfig);

	}

	@Override
	public void onBackPressed() {

		if (this.fragment instanceof DesktopFragment) {

			this.finish();

		} else {

			this.selectItem(0);

		}
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {

			MainActivity.this.selectItem(position);

		}
	}

}
