package br.com.natanael.task;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

import br.com.natanael.Application.GCMApplication;
import br.com.natanael.gcm.Constantes;

import com.google.android.gms.gcm.GoogleCloudMessaging;






import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class EnviaMensagemGCMServerTask extends AsyncTask<Void, Void, String>{

	private GCMApplication app;
	private static final String REGISTRATION_ID = "registrationId";
	AtomicInteger msgId = new AtomicInteger();
	
	public EnviaMensagemGCMServerTask (GCMApplication app){
		this.app = app;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		
		String registrationID = this.app.getSharedPreferences("configs", Activity.MODE_PRIVATE).getString(REGISTRATION_ID, null);
		if(registrationID != null){
			/*Bundle data = new Bundle();
	        data.putString("my_message", "Hello World");
	        data.putString("my_action",
	                "com.google.android.gcm.demo.app.ECHO_NOW");
	        String id = Integer.toString(msgId.incrementAndGet());
	        try{
	        	GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this.app);
	        	gcm.send(Constantes.GCM_SERVER_ID + "@gcm.googleapis.com", id, data);
	        	Log.i("GCM_SERVER_MSG","Enviando requisição ao GCM Server:\n"+id+"\n"+data);
	        }catch(Exception e){
	        	Log.e("GCM_SEND_MSG","Erro ao enviar requisição ao servidor! "+e.getMessage());
	        }
			try{
				String msgJson = "{\"data\": { \"mensagem\":\"Mensagem de Teste\"},\"registration_ids\":[\"" + registrationID
					+"\"]}";
				  
				    byte[] data = msgJson.getBytes("UTF-8");  
				    
				    URL url = new URL(  
				      "https://android.googleapis.com/gcm/send");  
				  
				    HttpsURLConnection conexao =   
				      (HttpsURLConnection)url.openConnection();  
				  
				    conexao.addRequestProperty("Authorization",   
				      "key="+"AIzaSyCQctafU1AteIHxHhDf-0hB4-fcXn-oUfU");  
				    conexao.addRequestProperty(  
				      "Content-Type", "application/json");  
				    conexao.setRequestMethod("POST");  
				    conexao.setDoOutput(true);  
				    conexao.setUseCaches(false);  
				    conexao.connect();  
				    
				    OutputStream os = conexao.getOutputStream();  
				    os.write(data);  
				    os.close();  
				    
				    if (conexao.getResponseCode() ==  HttpURLConnection.HTTP_OK){  
		    	  
				    	Log.i("GCM_SEND_MSG","Mensagem enviada");
		    	    } else {  
		    	    	Log.e("GCM_SEND_MSG","Erro ao enviar requisição ao servidor! "+ conexao.getResponseCode() +" - "+conexao.getResponseMessage());  
		    	    }  
			}catch(Exception e){
				Log.e("GCM_SEND_MSG","Erro ao enviar requisição ao servidor! "+e.getMessage());
			}*/
			
			
		}else{
			Log.e("GCM_SERVER_MSG","RegistrationID é nulo");
		}
		Log.i("GCM_SERVER_MSG","Requisição ao GCM Server enviada");
		return null;
	}

}
