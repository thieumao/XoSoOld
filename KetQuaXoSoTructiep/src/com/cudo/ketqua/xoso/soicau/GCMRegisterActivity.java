package com.cudo.ketqua.xoso.soicau;

import static com.cudo.ketqua.xoso.soicau.GCMCommonUtilities.SENDER_ID;
import static com.cudo.ketqua.xoso.soicau.GCMCommonUtilities.SERVER_URL;

import com.cudo.ketqua.xoso.soicau.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GCMRegisterActivity extends Activity {
	// alert dialog manager
	GCMAlertDialogManager alert = new GCMAlertDialogManager();
	
	// Internet detector
	GCMConnectionDetector cd;
	
	// UI elements
	EditText txtName;
	EditText txtEmail;
	
	// Register button
	Button btnRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gcmactivity_register);
		
		cd = new GCMConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(GCMRegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(GCMRegisterActivity.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			 return;
		}
		
		txtName = (EditText) findViewById(R.id.txtName);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		
		// Nhay luon
		Intent i = new Intent(getApplicationContext(), GCMMainActivity.class);
		
		String sDeviceID = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
//		TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//		String sPhoneNumber = tMgr.getLine1Number();
//		String sEmail = GCMGetInfoUser.getEmail(this);
//		GCMGetInfoUser giu = new GCMGetInfoUser();
//		String sDeviceName = giu.getDeviceName();
		
		//String namerandom = sDeviceID;
		//String emailrandom = sPhoneNumber + " " + sEmail + " " + sDeviceName;
		String namerandom = sDeviceID;
		String emailrandom = "duoc roi";
		i.putExtra("name", namerandom);
		i.putExtra("email", emailrandom);
		startActivity(i);
		finish();
//		// ket thuc nhay luon
		
		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String name = txtName.getText().toString();
				String email = txtEmail.getText().toString();
				
				// Check if user filled the form
				if(name.trim().length() > 0 && email.trim().length() > 0){
					// Launch Main Activity
					Intent i = new Intent(getApplicationContext(), GCMMainActivity.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
					i.putExtra("name", name);
					i.putExtra("email", email);
					startActivity(i);
					finish();
				}else{
					// user doen't filled that data
					// ask him to fill the form
					alert.showAlertDialog(GCMRegisterActivity.this, "Registration Error!", "Please enter your details", false);
				}
			}
		});
	}

}
