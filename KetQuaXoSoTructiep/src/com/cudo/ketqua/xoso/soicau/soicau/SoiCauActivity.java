package com.cudo.ketqua.xoso.soicau.soicau;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.JSONParser;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.startapp.android.publish.StartAppAd;

public class SoiCauActivity extends ActionBarActivity {

	private TextView tvDacBiet, tvNhatTo, tvSoiCau;
	private Button btSoiCau;
	private Spinner spSoiCau;
	private String dacbiet, nhatto;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soi_cau);
		
		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}

		tvDacBiet = (TextView) findViewById(R.id.textViewDacBiet);
		tvNhatTo = (TextView) findViewById(R.id.textViewNhatTo);
		tvSoiCau = (TextView) findViewById(R.id.textViewSoiCau);
		btSoiCau = (Button) findViewById(R.id.buttonSoiCauPascal);
		spSoiCau = (Spinner) findViewById(R.id.spinnerVungSoiCau);

		themItemSpinnerMien();

		btSoiCau.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (KiemTraMang(getApplicationContext())) {
					new ChayNgam().execute("");
				} else {
					Toast.makeText(SoiCauActivity.this,
							"Hãy kiểm tra lại Wifi/3G", Toast.LENGTH_SHORT)
							.show();
				}
				// dialog = new ProgressDialog(SoiCauActivity.this);
				// dialog.setMessage("Loading...");
				// dialog.setCancelable(true);
				// dialog.show();

				// if (KiemTraMang(getApplicationContext())) {
				// String vitri = String.valueOf(spSoiCau.getSelectedItemId());
				// int iViTri = Integer.valueOf(vitri);
				// String url = Variables.linkSoiCau;
				// JSONParser jParser = new JSONParser();
				// List<NameValuePair> p = new ArrayList<NameValuePair>();
				// p.add(new BasicNameValuePair("limit", "1"));
				// p.add(new BasicNameValuePair("vung",
				// Variables.TinhCaBaMien_LINKS[iViTri]));
				// JSONObject json = jParser.makeHttpRequest(url, "GET", p);
				// Log.d("Kiem tra json ", json.toString());
				// try {
				// JSONArray products = json.getJSONArray("message");
				// for (int i = 0; i < products.length(); i++) {
				// JSONObject c = products.getJSONObject(i);
				// String ketqua = c.getString("ketqua");
				// // String thoigian = c.getString("thoigian");
				// // Log.d("Ket qua: ", ketqua);
				// // Log.d("Thoi gian: ", thoigian);
				// ketqua = ketqua.replace(" ", "");
				// String[] mangKetQua = ketqua.split(",");
				// dacbiet = mangKetQua[0];
				// nhatto = mangKetQua[1];
				// // xu ly tai day
				// tvDacBiet.setText(dacbiet);
				// tvNhatTo.setText(nhatto);
				// tvSoiCau.setText(soicaupascal(dacbiet, nhatto));
				// }
				// } catch (JSONException e) {
				// e.printStackTrace();
				// }
				//
				// } else {
				// Toast.makeText(SoiCauActivity.this,
				// "Hãy kiểm tra lại Wifi/3G", Toast.LENGTH_SHORT)
				// .show();
				// }
				// if (dialog != null) {
				// dialog.dismiss();
				// }

			}
		});
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
		spSoiCau.setAdapter(dataAdapter);
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

	public static String soicaupascal(String dacbiet, String nhatto) {
		String p = dacbiet + nhatto;
		String xuat = p;
		// System.out.println(p);
		while (p.length() > 2) {
			String str = "";
			for (int i = 0; i < (p.length() - 1); i++) {
				int t = Integer.valueOf(p.charAt(i) - 48)
						+ Integer.valueOf(p.charAt(i + 1) - 48);
				String str1 = String.valueOf(t);
				str = str + str1.substring(str1.length() - 1, str1.length());
			}
			p = str;
			xuat = xuat + "\n" + p;
			// System.out.println(p);
		}
		return xuat;
	}

	class ChayNgam extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(SoiCauActivity.this);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params2) {
			if (KiemTraMang(getApplicationContext())) {
				String vitri = String.valueOf(spSoiCau.getSelectedItemId());
				int iViTri = Integer.valueOf(vitri);
				String url = Variables.linkSoiCau;
				JSONParser jParser = new JSONParser();
				List<NameValuePair> p = new ArrayList<NameValuePair>();
				p.add(new BasicNameValuePair("limit", "1"));
				p.add(new BasicNameValuePair("vung",
						Variables.TinhCaBaMien_LINKS[iViTri]));
				JSONObject json = jParser.makeHttpRequest(url, "GET", p);
				Log.d("Kiem tra json ", json.toString());
				try {
					JSONArray products = json.getJSONArray("message");
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						String ketqua = c.getString("ketqua");
						// String thoigian = c.getString("thoigian");
						// Log.d("Ket qua: ", ketqua);
						// Log.d("Thoi gian: ", thoigian);
						ketqua = ketqua.replace(" ", "");
						String[] mangKetQua = ketqua.split(",");
						dacbiet = mangKetQua[0];
						nhatto = mangKetQua[1];
						// xu ly tai day
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Toast.makeText(SoiCauActivity.this, "Hãy kiểm tra lại Wifi/3G",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			tvDacBiet.setText(dacbiet);
			tvNhatTo.setText(nhatto);
			tvSoiCau.setText(soicaupascal(dacbiet, nhatto));
			super.onPostExecute(result);
		}

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
