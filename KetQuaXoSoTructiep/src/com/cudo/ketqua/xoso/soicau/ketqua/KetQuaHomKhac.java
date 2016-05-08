package com.cudo.ketqua.xoso.soicau.ketqua;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.JSONParser;
import com.cudo.ketqua.xoso.soicau.utils.Variables;

public class KetQuaHomKhac extends ActionBarActivity {

	private ProgressDialog dialog;
	private Spinner spVung;
	private EditText etNgay;
	private Button btXem;
	private TextView chuGiaiTam, tvVung, tvThoiGian, tvkqdb, tvkq1, tvkq2,
			tvkq3, tvkq4, tvkq5, tvkq6, tvkq7, tvkq8, dau0, dau1, dau2, dau3,
			dau4, dau5, dau6, dau7, dau8, dau9, duoi0, duoi1, duoi2, duoi3,
			duoi4, duoi5, duoi6, duoi7, duoi8, duoi9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ket_qua_hom_khac);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		spVung = (Spinner) findViewById(R.id.spinnerVungHomKhac);
		etNgay = (EditText) findViewById(R.id.editTextNgayHomKhac);
		btXem = (Button) findViewById(R.id.buttonXemHomKhac);
		tvVung = (TextView) findViewById(R.id.tvVungHomKhac);
		tvThoiGian = (TextView) findViewById(R.id.tvThoiGianHomKhac);
		chuGiaiTam = (TextView) findViewById(R.id.chuGiaiTam);
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
		themNgayHienTai();

		btXem.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// Che ban phim
				hideSoftKeyboard(KetQuaHomKhac.this);
				resetKetQua();
				if (KiemTraMang(getApplicationContext())) {
					new ChayNgam().execute("");
				} else {
					Toast.makeText(KetQuaHomKhac.this,
							"Hãy kiểm tra lại Wifi/3G", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

	}

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	public void resetKetQua() {
		tvkqdb.setText("");
		tvkq1.setText("");
		tvkq2.setText("");
		tvkq3.setText("");
		tvkq4.setText("");
		tvkq5.setText("");
		tvkq6.setText("");
		tvkq7.setText("");
		tvkq8.setText("");
		chuGiaiTam.setText("");
		dau0.setText("");
		dau1.setText("");
		dau2.setText("");
		dau3.setText("");
		dau4.setText("");
		dau5.setText("");
		dau6.setText("");
		dau7.setText("");
		dau8.setText("");
		dau9.setText("");
		duoi0.setText("");
		duoi1.setText("");
		duoi2.setText("");
		duoi3.setText("");
		duoi4.setText("");
		duoi5.setText("");
		duoi6.setText("");
		duoi7.setText("");
		duoi8.setText("");
		duoi9.setText("");
	}

	public void themItemSpinnerMien() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Variables.TinhCaBaMien.length; i++) {
			list.add(Variables.TinhCaBaMien[i]);
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spVung.setAdapter(dataAdapter);
	}

	public void themNgayHienTai() {
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		String ngay = String.valueOf(today.monthDay);
		if (today.monthDay < 10) {
			ngay = "0" + ngay;
		}
		String thang = String.valueOf(today.month + 1);
		if (today.month + 1 < 10) {
			thang = "0" + thang;
		}
		String nam = String.valueOf(today.year);
		String strThoigian = ngay + "-" + thang + "-" + nam;
		etNgay.setText(strThoigian);
	}

	public Boolean KiemTraMang(Context con) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) con
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo wifiInfo = connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo mobileInfo = connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (wifiInfo.isConnected() || mobileInfo.isConnected()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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

	class ChayNgam extends AsyncTask<String, Void, Void> {

		private String strVungNgam = "Không tìm thấy kết quả";
		private String strThoiGianNgam = "";
		private String[] mangGiai = new String[9];
		private String checkSuccess = "0";
		private String ketqua;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(KetQuaHomKhac.this);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params2) {
			if (KiemTraMang(getApplicationContext())) {
				String vitri = String.valueOf(spVung.getSelectedItemId());
				int iViTri = Integer.valueOf(vitri);
				String pVung = Variables.TinhCaBaMien_LINKS[iViTri];
				String pThoigian = etNgay.getText().toString();
				pThoigian = pThoigian.replace("-", "/");
				pThoigian = pThoigian.replace(" ", "");
				String url = Variables.linkHomKhac;
				JSONParser jParser = new JSONParser();
				List<NameValuePair> p = new ArrayList<NameValuePair>();
				p.add(new BasicNameValuePair("vung", pVung));
				p.add(new BasicNameValuePair("thoigian", pThoigian));
				JSONObject json = jParser.makeHttpRequest(url, "GET", p);
				// Log.d("Kiem tra json ", json.toString());
				// String checkSuccess = "0";
				try {
					checkSuccess = json.getString("success");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				if (checkSuccess.equals("1")) {
					strVungNgam = "Xổ Số " + Variables.TinhCaBaMien[iViTri];
					try {
						JSONArray products = json.getJSONArray("message");
						for (int j = 0; j < products.length(); j++) {
							JSONObject c = products.getJSONObject(j);
							// Lay Thoi Gian
							strThoiGianNgam = c.getString("thoigian");
							// Lay Ket Qua va Xu Ly
							ketqua = c.getString("ketqua");
							ketqua = ketqua.replace(" ", "");
							String[] catKetQua = ketqua.split(",");
							// String[] mangGiai = new String[9];
							for (int i = 0; i < 9; i++) {
								if (i < catKetQua.length) {
									mangGiai[i] = catKetQua[i];
								} else {
									mangGiai[i] = "";
								}
							}
							for (int i = 0; i < mangGiai.length; i++) {
								if (mangGiai[i].length() == 0) {
									mangGiai[i] = "";
								}
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else {
				Toast.makeText(KetQuaHomKhac.this, "Hãy kiểm tra lại Wifi/3G",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			tvVung.setText(strVungNgam);
			tvThoiGian.setText(strThoiGianNgam);
			if (checkSuccess.equals("1")) {
				tvkqdb.setText(mangGiai[0].replace("-", " "));
				tvkq1.setText(mangGiai[1].replace("-", " "));
				tvkq2.setText(mangGiai[2].replace("-", " "));
				tvkq3.setText(mangGiai[3].replace("-", " "));
				tvkq4.setText(mangGiai[4].replace("-", " "));
				tvkq5.setText(mangGiai[5].replace("-", " "));
				tvkq6.setText(mangGiai[6].replace("-", " "));
				tvkq7.setText(mangGiai[7].replace("-", " "));
				String vitri = String.valueOf(spVung.getSelectedItemId());
				int iViTri = Integer.valueOf(vitri);
				if (iViTri > 0) {
					chuGiaiTam.setText("Giải Tám");
					tvkq8.setText(mangGiai[8].replace("-", " "));
					if ((ketqua.length() >= 98) && (ketqua.length() <= 99)) {
						ThongKe();
					}
				} else {
					if ((ketqua.length() >= 133) && (ketqua.length() <= 134)) {
						ThongKe();
					}
				}
			}
			super.onPostExecute(result);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.hom_khac, menu);
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

		if (item.getItemId() == R.id.action_refresh) {
			// Che ban phim
			hideSoftKeyboard(KetQuaHomKhac.this);
			resetKetQua();
			if (KiemTraMang(getApplicationContext())) {
				new ChayNgam().execute("");
			} else {
				Toast.makeText(KetQuaHomKhac.this, "Hãy kiểm tra lại Wifi/3G",
						Toast.LENGTH_SHORT).show();
			}
		}

		return super.onOptionsItemSelected(item);
	}

}
