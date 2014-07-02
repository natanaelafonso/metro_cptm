package br.com.natanael.Application;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import br.com.natanael.gcm.Constantes;
import br.com.natanael.task.EnviaMensagemGCMServerTask;
import br.com.natanael.task.RegistraDeviceTask;
import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class GCMApplication extends Application {
	
	private static final String REGISTERED = "registeredInGCM";
	private static final String REGISTRATION_ID = "registrationId";
	private SharedPreferences preferences;
	
	
	
	@Override
	public void onCreate(){
		super.onCreate();
		preferences = getSharedPreferences("configs", Activity.MODE_PRIVATE);
		initializeGCM();
	}
	
	
	public void initializeGCM(){
		if(!usuarioRegistrado()){
			new RegistraDeviceTask(this).execute();
		}else{
			String registrationID = preferences.getString(REGISTRATION_ID, null);
			Log.i("RegistroServer","Device já registrado! RegistrationId:"+registrationID);
			//new EnviaMensagemGCMServerTask(this).execute();
		}
	}
	
	private boolean usuarioRegistrado(){
		return preferences.getBoolean(REGISTERED, false);
				
	}
	
	public void respostaRegistroDoServidor(String registro){
		if(registro!=null){
			preferences.edit().putBoolean(REGISTERED, true).commit();
			preferences.edit().putString(REGISTRATION_ID, registro).commit();
			
		}
	}
	
}
