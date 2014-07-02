package br.com.natanael.gcm;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.natanael.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class GCMBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		SharedPreferences preferences;
		preferences = context.getSharedPreferences("configs", Activity.MODE_PRIVATE);
		
		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		
		//Bundle extras = intent.getExtras();
		//Set<String> sets = extras.keySet();
		//String msg = extras.get("mensagem").toString();
		String msg = intent.getSerializableExtra("mensagem").toString();
		Log.i("GCM_SEND_MSG","Chegou mensagem - "+ msg);
		try{
			JSONObject jMsg = new JSONObject(msg); 
		
			String linha = jMsg.getString("linha");
			String estado = jMsg.getString("estado");
			String anterior = jMsg.getString("anterior");
			String hora = jMsg.getString("hora");
			
			String informacao = jMsg.getString("atencao");
		
			int ledColor = 0xffffffff;
			int notificationId  = 0;
			int iconId = 0;
			
			if(linha.compareTo("Linha 1") == 0)
			{
				ledColor = 0xff0000ff;
				notificationId = 1;
				iconId = R.drawable.azul;
			}
			else if(linha.compareTo("Linha 2") == 0)
			{
				ledColor = 0xff00ff00;
				notificationId = 2;
				iconId = R.drawable.verde;
			}
			else if(linha.compareTo("Linha 3") == 0)
			{
				ledColor = 0xffff0000;
				notificationId = 3;
				iconId = R.drawable.vermelho;
			}
			else if(linha.compareTo("Linha 4") == 0)
			{
				ledColor = 0xffffff00;
				notificationId = 4;
				iconId = R.drawable.amarela;
			}
			else if(linha.compareTo("Linha 5") == 0)
			{
				ledColor = 0xffff00ff;
				notificationId = 5;
				iconId = R.drawable.lilas;
			}
			else
			{
				if(linha.compareTo("Linha 7 - rubi") == 0)
				{
					notificationId = 8;
				}
				else if(linha.compareTo("Linha 8 - diamante") == 0)
				{
					notificationId = 9;
				}
				else if(linha.compareTo("Linha 9 - esmeralda") == 0)
				{
					notificationId = 10;
				}
				else if(linha.compareTo("Linha 10 - turquesa") == 0)
				{
					notificationId = 11;
				}
				else if(linha.compareTo("Linha 11 - coral") == 0)
				{
					notificationId = 12;
				}
				else if(linha.compareTo("Linha 12 - safira") == 0)
				{
					notificationId = 13;
				}
				ledColor = 0xffff00ff;
				iconId = R.drawable.cptm;
			}
			
			Uri url = Uri.parse("http://www.metro.sp.gov.br/Sistemas/direto-do-metro-via4/MetroStatusLinha/mobile/smartPhone/diretoDoMetro.aspx");
			Intent irParaSiteMetro = new Intent(Intent.ACTION_VIEW,url);
			
			PendingIntent acaoPendente = PendingIntent.getActivity(context, 0, irParaSiteMetro, Intent.FLAG_ACTIVITY_CLEAR_TASK);
					
			Notification notificacao = new Notification.Builder(context)
	        			.setContentTitle(linha)
						//.setContentText(hora + " - " +linha+" " +estado)
	        			.setContentText(hora + ": "+ anterior  + " -> " +estado)
						.setVibrate(new long[]{0,100,200,300})
						.setSmallIcon(iconId)
						.setLights(ledColor, 300, 1000)
						.setContentIntent(acaoPendente)
						.setAutoCancel(true)
						.build();
	
			NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	
			manager.notify(notificationId, notificacao);
			
			preferences.edit().putString(linha, anterior + "->" +estado).commit();
			if(informacao != null){
				preferences.edit().putBoolean("msg"+linha,true).commit();
				preferences.edit().putString("info"+linha, informacao).commit();
			}else{
				preferences.edit().putBoolean("msg"+linha,false).commit();
				preferences.edit().putString("info"+linha, "").commit();
			}
			preferences.edit().putString("hora",data + " às "+hora).commit();
			
			
			
			
		}catch (Exception e){
			throw new RuntimeException();
		}
	}

}
