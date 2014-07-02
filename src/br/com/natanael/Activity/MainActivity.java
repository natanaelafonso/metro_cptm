package br.com.natanael.Activity;

import java.util.ArrayList;
import java.util.List;

import br.com.natanael.R;
import br.com.natanael.Adapter.ListaLinhasAdapter;
import br.com.natanael.Modelo.Linha;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<Linha> linhas;
	
	private ListView listaLinhas;
	private Button botaoAtualizar;
	private TextView txtAtualizado;
	
	private SharedPreferences preferences;
	
	String[] nomeLinhas = {"Linha 1",
			"Linha 2",
			"Linha 3",
			"Linha 4",
			"Linha 5",
			"Linha 7 - rubi",
			"Linha 8 - diamante",
			"Linha 9 - esmeralda",
			"Linha 10 - turquesa",
			"Linha 11 - coral",
			"Linha 12 - safira"};
		
	int[] iconeLinha = {R.drawable.azul,
			R.drawable.verde,
			R.drawable.vermelho,
			R.drawable.amarela,
			R.drawable.lilas,
			R.drawable.cptm};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listaLinhas = (ListView) findViewById(R.id.listaLinhas);
		
		botaoAtualizar = (Button) findViewById(R.id.botaoAtualizar);
		
		txtAtualizado = (TextView) findViewById(R.id.txtAtualizado);
		
		botaoAtualizar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				atualizaLinhas();
				
			}
		});
		
		atualizaLinhas();
		
	}
	
	public void atualizaLinhas(){
		linhas = new ArrayList<Linha>();
		
		int numeroLinha=0;
		
		this.preferences = getSharedPreferences("configs", Activity.MODE_PRIVATE);
		for(String linha : nomeLinhas)
		{
			String estado = preferences.getString(linha, null);
			boolean temInformacao = preferences.getBoolean("msg"+linha, false);
			if(estado != null)
			{
				String[] estados = estado.split("->");
				String estadoAtual = estados[1];
				//String estadoAnterior = estados[0];
				
				Linha l = new Linha();
				
				l.setNomeLinha(linha);
				
				if(estadoAtual.compareTo(Linha.estado.OPERANDO.toString())==0)
				{
					l.setEstadoLinha(Linha.estado.OPERANDO);
				}
				else if(estadoAtual.compareTo(Linha.estado.ATENCAO.toString())==0)
				{
					l.setEstadoLinha(Linha.estado.ATENCAO);
				}
				else
				{
					l.setEstadoLinha(Linha.estado.DESATIVADA);
				}
				if(numeroLinha <= 4)
					l.setIconeLinha(iconeLinha[numeroLinha]);
				else
					l.setIconeLinha(iconeLinha[5]);
				
				if(temInformacao){
					String informacao = preferences.getString("info"+linha, null);
					l.setInformacaoLinha(informacao);
				}
				else
				{
					l.setInformacaoLinha(null);
				}
				
				linhas.add(l);
				numeroLinha ++;
				
			}
		}
		if(linhas.size() > 0)
		{
			txtAtualizado.setText("Atualizado em "+preferences.getString("hora", "não disponível"));
			ListaLinhasAdapter adapter = new ListaLinhasAdapter(this, linhas);
			this.listaLinhas.setAdapter(adapter);
		}
	}



}
