package com.cudo.ketqua.xoso.soicau;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	private int SPLASH_DISPLAY_LENGHT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);

		// MyBanner banner = new MyBanner();
		// int numberView = banner.numberShow(this, "checkInfoShow.dat");
		// if (numberView > 1) {
		// try {
		// AdFlexAds adFlexAds = new AdFlexAds(this);
		// adFlexAds.show();
		// } catch (Exception e) {
		//
		// }
		// }

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent mainIntent = new Intent(Splash.this, MainActivity.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

}
