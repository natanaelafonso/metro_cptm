package br.com.natanael.gcm;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class InformacoesDoUsuario {

	public static String getEmail(Context context){
		AccountManager aM = AccountManager.get(context);
		Account account = getAccount(aM);
		
		if(account == null)
			return null;
		
		return account.name;
	}
	
	private static Account getAccount(AccountManager aM){
		Account[] accounts = aM.getAccountsByType("com.google");
		Account account = null;
		
		if(accounts.length > 0)
			account = accounts[0];
		
		return account;
	}
	
}
