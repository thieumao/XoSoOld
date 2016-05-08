package com.cudo.ketqua.xoso.soicau.quaythu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.startapp.android.publish.StartAppAd;

public class QuayThuActivity extends ActionBarActivity {

	ProgressBar bar;
	Handler handler;
	AtomicBoolean isrunning = new AtomicBoolean(false);

	private Spinner spinnerMien;
	private Button btQuayThu;
	private TextView tvPhanTram, tvkqdb, tvkq1, tvkq2, tvkq3, tvkq4, tvkq5,
			tvkq6, tvkq7, tvkq8, dau0, dau1, dau2, dau3, dau4, dau5, dau6,
			dau7, dau8, dau9, duoi0, duoi1, duoi2, duoi3, duoi4, duoi5, duoi6,
			duoi7, duoi8, duoi9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quay_thu);
		
		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}

		bar = (ProgressBar) findViewById(R.id.progressBarPhanTram);
		btQuayThu = (Button) findViewById(R.id.buttonQuayThu);
		spinnerMien = (Spinner) findViewById(R.id.spinnerMien);
		tvPhanTram = (TextView) findViewById(R.id.textViewPhanTram);
		tvkqdb = (TextView) findViewById(R.id.tvkqdb);
		tvkq1 = (TextView) findViewById(R.id.tvkq1);
		tvkq2 = (TextView) findViewById(R.id.tvkq2);
		tvkq3 = (TextView) findViewById(R.id.tvkq3);
		tvkq4 = (TextView) findViewById(R.id.tvkq4);
		tvkq5 = (TextView) findViewById(R.id.tvkq5);
		tvkq6 = (TextView) findViewById(R.id.tvkq6);
		tvkq7 = (TextView) findViewById(R.id.tvkq7);
		tvkq8 = (TextView) findViewById(R.id.tvkq8);
		dau0 = (TextView) findViewById(R.id.dau0);
		dau1 = (TextView) findViewById(R.id.dau1);
		dau2 = (TextView) findViewById(R.id.dau2);
		dau3 = (TextView) findViewById(R.id.dau3);
		dau4 = (TextView) findViewById(R.id.dau4);
		dau5 = (TextView) findViewById(R.id.dau5);
		dau6 = (TextView) findViewById(R.id.dau6);
		dau7 = (TextView) findViewById(R.id.dau7);
		dau8 = (TextView) findViewById(R.id.dau8);
		dau9 = (TextView) findViewById(R.id.dau9);
		duoi0 = (TextView) findViewById(R.id.duoi0);
		duoi1 = (TextView) findViewById(R.id.duoi1);
		duoi2 = (TextView) findViewById(R.id.duoi2);
		duoi3 = (TextView) findViewById(R.id.duoi3);
		duoi4 = (TextView) findViewById(R.id.duoi4);
		duoi5 = (TextView) findViewById(R.id.duoi5);
		duoi6 = (TextView) findViewById(R.id.duoi6);
		duoi7 = (TextView) findViewById(R.id.duoi7);
		duoi8 = (TextView) findViewById(R.id.duoi8);
		duoi9 = (TextView) findViewById(R.id.duoi9);

		themItemSpinnerMien();

		btQuayThu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				doStart();
			}
		});

		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// msg.arg1 là giá trị được trả về trong message
				// của tiến trình con
				bar.setProgress(msg.arg1);
				tvPhanTram.setText(msg.arg1 + "%");

				if (String.valueOf(spinnerMien.getSelectedItem()).equals(
						"Miền Bắc")) {
					TextView chuGiaiTam = (TextView) findViewById(R.id.chuGiaiTam);
					chuGiaiTam.setText("");
					tvkqdb.setText(N(5));
					tvkq1.setText(N(5));
					tvkq2.setText(N(5) + " " + N(5));
					tvkq3.setText(N(5) + " " + N(5) + " " + N(5) + " " + N(5)
							+ " " + N(5) + " " + N(5));
					tvkq4.setText(N(4) + " " + N(4) + " " + N(4) + " " + N(4));
					tvkq5.setText(N(4) + " " + N(4) + " " + N(4) + " " + N(4)
							+ " " + N(4) + " " + N(4));
					tvkq6.setText(N(3) + " " + N(3) + " " + N(3));
					tvkq7.setText(N(2) + " " + N(2) + " " + N(2) + " " + N(2));
					tvkq8.setText("");
				} else {
					TextView chuGiaiTam = (TextView) findViewById(R.id.chuGiaiTam);
					chuGiaiTam.setText("Giải tám");
					tvkqdb.setText(N(6));
					tvkq1.setText(N(5));
					tvkq2.setText(N(5));
					tvkq3.setText(N(5) + " " + N(5));
					tvkq4.setText(N(5) + " " + N(5) + " " + N(5) + " " + N(5)
							+ " " + N(5) + " " + N(5) + " " + N(5));
					tvkq5.setText(N(4));
					tvkq6.setText(N(4) + " " + N(4) + " " + N(4));
					tvkq7.setText(N(3));
					tvkq8.setText(N(2));
				}
				if (msg.arg1 == 100) {
					ThongKe();
				}
			}
		};

	}

	public void ThongKe() {
		String strThongKe = tvkqdb.getText().toString().trim() + " "
				+ tvkq1.getText().toString().trim() + " "
				+ tvkq2.getText().toString().trim() + " "
				+ tvkq3.getText().toString().trim() + " "
				+ tvkq4.getText().toString().trim() + " "
				+ tvkq5.getText().toString().trim() + " "
				+ tvkq6.getText().toString().trim() + " "
				+ tvkq7.getText().toString().trim() + " "
				+ tvkq8.getText().toString().trim();
		strThongKe = strThongKe.replace("  ", " ");
		strThongKe = strThongKe.trim();
		Log.v("thong ke", strThongKe);
		String[] mangThongKe = strThongKe.split(" ");
		String[] mang2TK = new String[mangThongKe.length];
		String[] dau = new String[mangThongKe.length];
		String[] duoi = new String[mangThongKe.length];
		for (int i = 0; i < mangThongKe.length; i++) {
			String s = mangThongKe[i];
			int l = s.length();
			mang2TK[i] = s.substring(l - 2, l);
			dau[i] = s.substring(l - 2, l - 1);
			duoi[i] = s.substring(l - 1, l);
			Log.v("So " + i, mang2TK[i] + "");
		}

		String[] sodau = new String[10];
		String[] soduoi = new String[10];
		for (int i = 0; i <= 9; i++) {
			sodau[i] = "";
			soduoi[i] = "";
		}
		for (int i = 0; i < mangThongKe.length; i++) {
			int da = Integer.valueOf(dau[i]);
			sodau[da] = sodau[da] + " " + duoi[i];
			int du = Integer.valueOf(duoi[i]);
			soduoi[du] = soduoi[du] + " " + dau[i];
		}

		dau0.setText(sodau[0]);
		dau1.setText(sodau[1]);
		dau2.setText(sodau[2]);
		dau3.setText(sodau[3]);
		dau4.setText(sodau[4]);
		dau5.setText(sodau[5]);
		dau6.setText(sodau[6]);
		dau7.setText(sodau[7]);
		dau8.setText(sodau[8]);
		dau9.setText(sodau[9]);

		duoi0.setText(soduoi[0]);
		duoi1.setText(soduoi[1]);
		duoi2.setText(soduoi[2]);
		duoi3.setText(soduoi[3]);
		duoi4.setText(soduoi[4]);
		duoi5.setText(soduoi[5]);
		duoi6.setText(soduoi[6]);
		duoi7.setText(soduoi[7]);
		duoi8.setText(soduoi[8]);
		duoi9.setText(soduoi[9]);

	}

	public static String N(int so) {
		Random rd = new Random();
		int Max = 0, Min = 0;
		String strNgayNhien = "";
		switch (so) {
		case 2:
			Max = 99;
			break;
		case 3:
			Max = 999;
			break;
		case 4:
			Max = 9999;
			break;
		case 5:
			Max = 99999;
			break;
		case 6:
			Max = 999999;
			break;
		default:
			break;
		}
		strNgayNhien = String.valueOf(rd.nextInt((Max - Min + 1) + Min));
		while (strNgayNhien.length() < so) {
			strNgayNhien = "0" + strNgayNhien;
		}
		return strNgayNhien;
	}

	// Them Item 3 Mien
	public void themItemSpinnerMien() {
		List<String> list = new ArrayList<String>();
		list.add("Miền Bắc");
		list.add("Miền Trung");
		list.add("Miền Nam");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMien.setAdapter(dataAdapter);
	}

	public void doStart() {
		bar.setProgress(0);
		isrunning.set(false);
		// tạo 1 tiến trình CON
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				// vòng lặp chạy 100 lần
				for (int i = 1; i <= 100 && isrunning.get(); i++) {
					// cho tiến trình tạm ngừng 100 mili second
					SystemClock.sleep(5);
					// lấy message từ Main thread
					Message msg = handler.obtainMessage();
					// gán giá trị vào cho arg1 để gửi về Main thread
					msg.arg1 = i;
					// gửi lại Message này về cho Main Thread
					handler.sendMessage(msg);
				}
			}
		});
		isrunning.set(true);
		// kích hoạt tiến trình
		th.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
//			Intent intent = new Intent(this, MainActivity.class);
//			startActivity(intent);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
