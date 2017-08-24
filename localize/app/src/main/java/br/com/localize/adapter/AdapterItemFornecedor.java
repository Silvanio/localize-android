package br.com.localize.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.localize.R;
import br.com.localize.dto.FornecedorDTO;

public class AdapterItemFornecedor extends BaseAdapter {

	private final LayoutInflater mInflater;

	private final List<FornecedorDTO> itens;

	public AdapterItemFornecedor( final Context context, final List<FornecedorDTO> itens ) {

		this.mInflater = LayoutInflater.from(context);

		this.itens = itens;

	}

	@Override
	public int getCount() {

		return this.itens.size();
	}

	@Override
	public Object getItem(final int position) {

		return this.itens.get(position);
	}

	@Override
	public long getItemId(final int position) {

		return position;
	}

	@Override
	public View getView(final int position, View view, final ViewGroup parent) {

		ItemSuporteFornecedor itemHolder;

		if (view == null) {

			view = this.mInflater.inflate(R.layout.item_fornecedor_lista, null);

			itemHolder = new ItemSuporteFornecedor();

			itemHolder.nome = ( (TextView) view.findViewById(R.id.nome_item) );

			itemHolder.email = ( (TextView) view.findViewById(R.id.email_item) );
			
			itemHolder.descricao = ( (TextView) view.findViewById(R.id.descricao_item) );

			itemHolder.distancia = ( (TextView) view.findViewById(R.id.distancia_item) );
			
			itemHolder.img = ( (ImageView) view.findViewById(R.id.logo_empresa) );

			view.setTag(itemHolder);
		} else {

			itemHolder = (ItemSuporteFornecedor) view.getTag();

		}

		final FornecedorDTO fornecedorItem = this.itens.get(position);

		itemHolder.nome.setText(fornecedorItem.getNome());

		itemHolder.descricao.setText(fornecedorItem.getDescricao());
		
		itemHolder.email.setText(fornecedorItem.getEmail());

		if(fornecedorItem.getDitancia() != null){
			
			itemHolder.distancia.setText(fornecedorItem.getDitancia().toString()+" Km");
			
		}
		
		itemHolder.img.setImageBitmap(fornecedorItem.getIcon());

		return view;

	}
	

	class ItemSuporteFornecedor {

		ImageView img;

		TextView nome;

		TextView email;

		TextView descricao;

		TextView distancia;

	}

}
