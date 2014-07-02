package br.com.natanael.task;

import br.com.natanael.Application.GCMApplication;
import br.com.natanael.gcm.Constantes;
import br.com.natanael.gcm.InformacoesDoUsuario;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.os.AsyncTask;
import android.util.Log;

public class RegistraDeviceTask extends AsyncTask <Void,Void,String>{

	private GCMApplication app;
	
	public RegistraDeviceTask (GCMApplication app){
		this.app = app;
	}
	
	@Override
	protected String doInBackground(Void... params) {

		String registrationId = null;
		String email = InformacoesDoUsuario.getEmail(this.app);
		
		try{
			Log.i("GCM_SERVER_REGISTRATION","Registro Iniciado");
			GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this.app);
			registrationId = gcm.register(Constantes.GCM_SERVER_ID);
			
			
			Log.i("GCM_SERVER_REGISTRATION",email+registrationId);
			//new WebClient("device/register/"+email+"/"+registratinId).post();
			
		}catch (Exception e){
			Log.e("GCM_SERVER_REGISTRATION", "Problema no registro do device no server! " + e.getMessage());
		}
		
		return registrationId;
	}
	
	@Override
	protected void onPostExecute(String result){
		app.respostaRegistroDoServidor(result);
	}
	

}
