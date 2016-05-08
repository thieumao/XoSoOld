package com.cudo.ketqua.xoso.soicau.thongke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.startapp.android.publish.StartAppAd;

public class ThongKeActivity extends ActionBarActivity implements
		OnClickListener {

	private Button btThongKeDau, btThongKeDuoi, btThongKe0099, btThongKeDuDoan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_ke);
		
		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}

//		try {
//			// Startapp banner
//			StartAppSearch.init(this, "104144450", "203881518");
//			StartAppSearch.showSearchBox(this);
//			StartAppAd.init(this, "104144450", "203881518");
//		} catch (Exception e) {
//		}

		btThongKeDau = (Button) findViewById(R.id.buttonThongKeDau);
		btThongKeDuoi = (Button) findViewById(R.id.buttonThongKeDuoi);
		btThongKe0099 = (Button) findViewById(R.id.buttonThongKe0099);
		btThongKeDuDoan = (Button) findViewById(R.id.buttonThongKeDuDoan);
		btThongKeDau.setOnClickListener(this);
		btThongKeDuoi.setOnClickListener(this);
		btThongKe0099.setOnClickListener(this);
		btThongKeDuDoan.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.buttonThongKeDau:
			intent = new Intent(this, ThongKeDau.class);
			startActivity(intent);
			break;
		case R.id.buttonThongKeDuoi:
			intent = new Intent(this, ThongKeDuoi.class);
			startActivity(intent);
			break;
		case R.id.buttonThongKe0099:
			intent = new Intent(this, ThongKe0099.class);
			startActivity(intent);
			break;
		case R.id.buttonThongKeDuDoan:
			intent = new Intent(this, ThongKeDuDoan.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			// Intent intent = new Intent(this, MainActivity.class);
			// startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
