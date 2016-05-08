package com.cudo.ketqua.xoso.soicau.phantich;

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
import com.startapp.android.publish.StartAppAd;

public class PhanTichActivity extends ActionBarActivity {

	private ProgressDialog dialog;
	private Spinner spVung, spGiai;
	private EditText etLanQuay, etSo;
	private Button btPhanTich;
	private TextView tvDauLoai, tvDauLuot, tvDauTile, tvDuoiLoai, tvDuoiLuot,
			tvDuoiTile, tvCapLoai, tvCapLuot, tvCapTile, tvDoanLoai,
			tvDoanLuot, tvDoanTile, tvLoai, tvLuot, tvTile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phan_tich);
		
		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}
		
		// tat ban phim
		try {
			this.getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		} catch (Exception e) {
		}


		spVung = (Spinner) findViewById(R.id.spinnerVungPhanTich);
		spGiai = (Spinner) findViewById(R.id.spinnerGiaiPhanTich);
		etLanQuay = (EditText) findViewById(R.id.editTextLanQuayPhanTich);
		etSo = (EditText) findViewById(R.id.editTextSoPhanTich);
		btPhanTich = (Button) findViewById(R.id.btPhanTich);

		tvDauLoai = (TextView) findViewById(R.id.tvDauLoai);
		tvDauLuot = (TextView) findViewById(R.id.tvDauLuot);
		tvDauTile = (TextView) findViewById(R.id.tvDauTile);

		tvDuoiLoai = (TextView) findViewById(R.id.tvDuoiLoai);
		tvDuoiLuot = (TextView) findViewById(R.id.tvDuoiLuot);
		tvDuoiTile = (TextView) findViewById(R.id.tvDuoiTile);

		tvCapLoai = (TextView) findViewById(R.id.tvCapLoai);
		tvCapLuot = (TextView) findViewById(R.id.tvCapLuot);
		tvCapTile = (TextView) findViewById(R.id.tvCapTile);

		tvDoanLoai = (TextView) findViewById(R.id.tvDoanLoai);
		tvDoanLuot = (TextView) findViewById(R.id.tvDoanLuot);
		tvDoanTile = (TextView) findViewById(R.id.tvDoanTile);

		tvLoai = (TextView) findViewById(R.id.tvLoai);
		tvLuot = (TextView) findViewById(R.id.tvLuot);
		tvTile = (TextView) findViewById(R.id.tvTile);

		themItemSpinnerVung();
		themItemSpinnerGiai();
		
//		etLanQuay.setText("30");
//		etSo.setText("00");

		btPhanTich.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				try {
					hideSoftKeyboard(PhanTichActivity.this);
				} catch (Exception e) {
				}
				if (KiemTraMang(getApplicationContext())) {
					if (etSo.getText().length() == 2) {
						new ChayNgam().execute("");
					} else {
						Toast.makeText(PhanTichActivity.this,
								"Giá trị nhập không hợp lệ!",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(PhanTichActivity.this,
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

	public void themItemSpinnerVung() {
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

	public void themItemSpinnerGiai() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Variables.GiaiPhanTich.length; i++) {
			list.add(Variables.GiaiPhanTich[i]);
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spGiai.setAdapter(dataAdapter);
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

	class ChayNgam extends AsyncTask<String, Void, Void> {

		private String soDau = "", luotDau = "", phantramDau = "", soDuoi = "",
				luotDuoi = "", phantramDuoi = "", so0099 = "", luot0099 = "",
				phantram0099 = "", soDoan = "", luotDoan = "",
				phantramDoan = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(PhanTichActivity.this);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params2) {
			if (KiemTraMang(getApplicationContext())) {
				// Lay Vung
				String vitriVung = String.valueOf(spVung.getSelectedItemId());
				int iViTriVung = Integer.valueOf(vitriVung);
				String pVung = Variables.TinhCaBaMien_LINKS[iViTriVung];
				// Lay Giai
				String vitriGiai = String.valueOf(spGiai.getSelectedItemId());
				int iViTriGiai = Integer.valueOf(vitriGiai);
				String pGiai = Variables.GiaiPhanTich_LINKS[iViTriGiai];
				// Lay Lan Quay
				String pLanquay = etLanQuay.getText().toString();
				// Lay So, Dau, Duoi
				String pSo = etSo.getText().toString();
				String pDau = pSo.substring(0, 1);
				String pDuoi = pSo.substring(1, 2);

				/*
				 * Lay Thong Ke Dau
				 */
				try {
					List<NameValuePair> p = new ArrayList<NameValuePair>();
					p.add(new BasicNameValuePair("dauduoi", "dau"));
					p.add(new BasicNameValuePair("vung", pVung));
					p.add(new BasicNameValuePair("loai", pGiai));
					p.add(new BasicNameValuePair("limit", pLanquay));
					String url = Variables.linkDauDuoi;
					JSONParser jParser = new JSONParser();
					JSONObject json = jParser.makeHttpRequest(url, "GET", p);
					String checkSuccess = json.getString("success");
					if (checkSuccess.equals("1")) {
						try {
							JSONArray products = json.getJSONArray("message");
							for (int j = 0; j < products.length(); j++) {
								JSONObject c = products.getJSONObject(j);
								// Lay So
								String strDayso = c.getString("dayso");
								strDayso = strDayso.replace(" ", "");
								String[] catDayso = strDayso.split(",");
								// Lay Xuat Hien
								String strXuathien = c.getString("dayxuathien");
								strXuathien = strXuathien.replace(" ", "");
								String[] catDayxuathien = strXuathien
										.split(",");
								// Lay Phan Tram
								String strDayphantram = c
										.getString("dayphantram");
								strDayphantram = strDayphantram
										.replace(" ", "");
								String[] catDayphantram = strDayphantram
										.split(",");

								// Lay Ket Qua Dau
								int vitri = 0;
								for (int i = 0; i < catDayso.length; i++) {
									if (catDayso[i].equals(pDau)) {
										vitri = i;
									}
								}
								soDau = catDayso[vitri];
								luotDau = catDayxuathien[vitri];
								phantramDau = catDayphantram[vitri];

							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				/*
				 * Lay Thong Ke Duoi
				 */
				try {
					List<NameValuePair> p = new ArrayList<NameValuePair>();
					p.add(new BasicNameValuePair("dauduoi", "duoi"));
					p.add(new BasicNameValuePair("vung", pVung));
					p.add(new BasicNameValuePair("loai", pGiai));
					p.add(new BasicNameValuePair("limit", pLanquay));
					String url = Variables.linkDauDuoi;
					JSONParser jParser = new JSONParser();
					JSONObject json = jParser.makeHttpRequest(url, "GET", p);
					String checkSuccess = json.getString("success");
					if (checkSuccess.equals("1")) {
						try {
							JSONArray products = json.getJSONArray("message");
							for (int j = 0; j < products.length(); j++) {
								JSONObject c = products.getJSONObject(j);
								// Lay So
								String strDayso = c.getString("dayso");
								strDayso = strDayso.replace(" ", "");
								String[] catDayso = strDayso.split(",");
								// Lay Xuat Hien
								String strXuathien = c.getString("dayxuathien");
								strXuathien = strXuathien.replace(" ", "");
								String[] catDayxuathien = strXuathien
										.split(",");
								// Lay Phan Tram
								String strDayphantram = c
										.getString("dayphantram");
								strDayphantram = strDayphantram
										.replace(" ", "");
								String[] catDayphantram = strDayphantram
										.split(",");

								// Lay Ket Qua Duoi
								int vitri = 0;
								for (int i = 0; i < catDayso.length; i++) {
									if (catDayso[i].equals(pDuoi)) {
										vitri = i;
									}
								}
								soDuoi = catDayso[vitri];
								luotDuoi = catDayxuathien[vitri];
								phantramDuoi = catDayphantram[vitri];
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				/*
				 * Lay Thong Ke 00-99
				 */
				try {
					List<NameValuePair> p = new ArrayList<NameValuePair>();
					p.add(new BasicNameValuePair("vung", pVung));
					p.add(new BasicNameValuePair("loai", pGiai));
					p.add(new BasicNameValuePair("limit", pLanquay));
					String url = Variables.link0099;
					JSONParser jParser = new JSONParser();
					JSONObject json = jParser.makeHttpRequest(url, "GET", p);
					String checkSuccess = json.getString("success");
					if (checkSuccess.equals("1")) {
						try {
							JSONArray products = json.getJSONArray("message");
							for (int j = 0; j < products.length(); j++) {
								JSONObject c = products.getJSONObject(j);
								// Lay So
								String strDayso = c.getString("dayso");
								strDayso = strDayso.replace(" ", "");
								String[] catDayso = strDayso.split(",");
								// Lay Xuat Hien
								String strXuathien = c.getString("dayxuathien");
								strXuathien = strXuathien.replace(" ", "");
								String[] catDayxuathien = strXuathien
										.split(",");
								// Lay Phan Tram
								String strDayphantram = c
										.getString("dayphantram");
								strDayphantram = strDayphantram
										.replace(" ", "");
								String[] catDayphantram = strDayphantram
										.split(",");

								// Lay Ket Qua 00-99
								int vitri = 0;
								for (int i = 0; i < catDayso.length; i++) {
									if (catDayso[i].equals(pSo)) {
										vitri = i;
									}
								}
								so0099 = catDayso[vitri];
								luot0099 = catDayxuathien[vitri];
								phantram0099 = catDayphantram[vitri];
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

				/*
				 * Lay Thong Ke Du Doan
				 */
				String pNgay = pSo;
				String url = Variables.linkThongKeDuDoan;
				JSONParser jParser = new JSONParser();
				List<NameValuePair> p = new ArrayList<NameValuePair>();
				// vung=xo-so-kon-tum&limit=70&loai=haicua
				p.add(new BasicNameValuePair("limit", pNgay));

				JSONObject json = jParser.makeHttpRequest(url, "GET", p);

				String checkSuccess = "0";
				try {
					checkSuccess = json.getString("success");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				if (checkSuccess.equals("1")) {
					// strVungNgam = "Kết quả thống kê giải "
					// + Variables.GiaiPhanTich[iViTriGiai] + " của "
					// + Variables.TinhCaBaMien[iViTriVung] + " trong "
					// + pNgay + " lần quay.";

					try {
						JSONArray products = json.getJSONArray("message");
						for (int j = 0; j < products.length(); j++) {
							JSONObject c = products.getJSONObject(j);
							// Lay So
							String strDayso = c.getString("dayso");
							strDayso = strDayso.replace(" ", "");
							String[] catDayso = strDayso.split(",");
							// Lay Xuat Hien
							String strXuathien = c.getString("dayxuathien");
							strXuathien = strXuathien.replace(" ", "");
							String[] catDayxuathien = strXuathien.split(",");
							// Lay Phan Tram
							String strDayphantram = c.getString("dayphantram");
							strDayphantram = strDayphantram.replace(" ", "");
							String[] catDayphantram = strDayphantram.split(",");

							// Lay Ket Qua Du Doan
							int vitri = 0;
							for (int i = 0; i < catDayso.length; i++) {
								if (catDayso[i].equals(pSo)) {
									vitri = i;
								}
							}
							soDoan = catDayso[vitri];
							luotDoan = catDayxuathien[vitri];
							phantramDoan = catDayphantram[vitri];
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			tvLoai.setText("Loại");
			tvLuot.setText("Lượt");
			tvTile.setText("Tỉ lệ");
			tvDauLoai.setText("Đầu " + soDau);
			tvDauLuot.setText(luotDau);
			tvDauTile.setText(phantramDau + "%");
			tvDuoiLoai.setText("Đuôi " + soDuoi);
			tvDuoiLuot.setText(luotDuoi);
			tvDuoiTile.setText(phantramDuoi + "%");
			tvCapLoai.setText("Cặp " + so0099);
			tvCapLuot.setText(luot0099);
			tvCapTile.setText(phantram0099 + "%");
			tvDoanLoai.setText("Đoán " + soDoan);
			tvDoanLuot.setText(luotDoan);
			tvDoanTile.setText(phantramDoan + "%");
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