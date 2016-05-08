package com.cudo.ketqua.xoso.soicau;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;

/**
 * This class uses the AccountManager to get the primary email address of the
 * current user.
 */
public class GCMGetInfoUser {

  public static String getEmail(Context context) {
    AccountManager accountManager = AccountManager.get(context); 
    Account account = getAccount(accountManager);

    if (account == null) {
      return null;
    } else {
      return account.name;
    }
  }

  private static Account getAccount(AccountManager accountManager) {
    Account[] accounts = accountManager.getAccountsByType("com.google");
    Account account;
    if (accounts.length > 0) {
      account = accounts[0];      
    } else {
      account = null;
    }
    return account;
  }
  
  
  public String getDeviceName() {
	  String manufacturer = Build.MANUFACTURER;
	  String model = Build.MODEL;
	  if (model.startsWith(manufacturer)) {
	    return capitalize(model);
	  } else {
	    return capitalize(manufacturer) + " " + model;
	  }
	}


	private String capitalize(String s) {
	  if (s == null || s.length() == 0) {
	    return "";
	  }
	  char first = s.charAt(0);
	  if (Character.isUpperCase(first)) {
	    return s;
	  } else {
	    return Character.toUpperCase(first) + s.substring(1);
	  }
	} 
}