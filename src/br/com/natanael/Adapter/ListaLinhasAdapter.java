package br.com.natanael.Adapter;

import java.util.List;

import br.com.natanael.R;
import br.com.natanael.Modelo.Linha;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaLinhasAdapter extends BaseAdapter{

	private final List<Linha> linhas;
	private final Activity activity;
	
	public ListaLinhasAdapter(Activity activity,List<Linha> linhas){
		this.activity = activity;
		this.linhas = linhas;
		
	}
	
	
	
	
	@Override
	public int getCount() {
		return linhas.size();
	}

	@Override
	public Object getItem(int position) {
		return linhas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = activity.getLayoutInflater().inflate(R.layout.item, null);
		
		Linha linha = linhas.get(position);
		
		ImageView icone = (ImageView) view.findViewById(R.id.iconeLinha);
		TextView nomeLinha = (TextView) view.findViewById(R.id.nomeLinha);
		TextView estadoLinha = (TextView) view.findViewById(R.id.estadoLinha);
		TextView informacaoLinha = (TextView) view.findViewById(R.id.informacaoLinha);
		
		
		icone.setImageResource(linha.getIconeLinha());
		nomeLinha.setText(linha.getNomeLinha());
		estadoLinha.setText(linha.getEstadoLinha().toString());
		if(linha.getInformacaoLinha().compareTo("") != 0)
		{
			informacaoLinha.setText(linha.getInformacaoLinha());
			view.setBackgroundColor(Color.YELLOW);
		}
		else
		{
			informacaoLinha.setText("");
			view.setBackgroundColor(Color.WHITE);
		}
		
		return view;
	}
	
	

}
