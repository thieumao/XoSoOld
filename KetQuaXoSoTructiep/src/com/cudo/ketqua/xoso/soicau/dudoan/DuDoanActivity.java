package com.cudo.ketqua.xoso.soicau.dudoan;

import java.io.UnsupportedEncodingException;
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
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.JSONParser;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.startapp.android.publish.StartAppAd;

public class DuDoanActivity extends ActionBarActivity {

	DuDoanMyAdapter adapter;
	ArrayList<DuDoanItemContent> items;
	ListView lvDuDoan;

	private ProgressDialog dialog;

	private EditText etTenBan, etKetQua, etBinhLuan;
	private Button btDuDoanKetQua;

	// private TextView tvServerTraVeDuDoan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_du_doan);
		
		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}
		
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// tvServerTraVeDuDoan = (TextView)
		// findViewById(R.id.tvServerTraVeDuDoan);
		btDuDoanKetQua = (Button) findViewById(R.id.btDuDoanKetQua);
		lvDuDoan = (ListView) findViewById(R.id.lvDuDoan);
		// etTenBan, etKetQua, etBinhLuan
		etTenBan = (EditText) findViewById(R.id.etTenBan);
		etKetQua = (EditText) findViewById(R.id.etKetQua);
		etBinhLuan = (EditText) findViewById(R.id.etBinhLuan);

		if (KiemTraMang(getApplicationContext())) {
			new ChayNgam().execute("");
		} else {
			Toast.makeText(DuDoanActivity.this, "Hãy kiểm tra lại Wifi/3G",
					Toast.LENGTH_SHORT).show();
		}

		try {
			Bundle bundle = getIntent().getExtras();
			String strTen = bundle.getString("strTen");
			String strSo = bundle.getString("strSo");
			String strChu = bundle.getString("strChu");
			// Xử lý
			strTen = strTen.trim();
			strSo = strSo.replace(" ", "");
			strChu = strChu.trim();
			// Gán vào edittext
			etTenBan.setText(strTen);
			etKetQua.setText(strSo);
			etBinhLuan.setText(strChu);
		} catch (Exception e) {
		}

		btDuDoanKetQua.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// Tat ban phim
				hideSoftKeyboard(DuDoanActivity.this);
				if (KiemTraMang(getApplicationContext())) {
					new ChayNgam().execute("");
				} else {
					Toast.makeText(DuDoanActivity.this,
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

	public static String encodebase64(String text) {
		String base64 = "";
		byte[] data;
		try {
			data = text.getBytes("UTF-8");
			base64 = Base64.encodeToString(data, Base64.DEFAULT);
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		return base64;
	}

	class ChayNgam extends AsyncTask<String, Void, Void> {

		// private String strVungNgam = "Không tìm thấy kết quả";
		private String checkSuccess = "0";
		// private String[] catDayso;
		// private String[] catDayxuathien;
		// private String[] catDayphantram;

		private String[] mangTen, mangKetqua, mangNoidung, mangThoigian;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(DuDoanActivity.this);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params2) {
			if (KiemTraMang(getApplicationContext())) {
				// Truyen du lieu
				String pLimit = "50";
				String pTenBan = encodebase64(etTenBan.getText().toString());
				String pKetQua = encodebase64(etKetQua.getText().toString());
				String pBinhLuan = encodebase64(etBinhLuan.getText().toString());
				// String pTenBan = etTenBan.getText().toString();
				// String pKetQua = etKetQua.getText().toString();
				// String pBinhLuan = etBinhLuan.getText().toString();

				Log.v("Xem lỗi font chữ không", pBinhLuan);
				List<NameValuePair> p = new ArrayList<NameValuePair>();
				p.add(new BasicNameValuePair("limit", pLimit));
				p.add(new BasicNameValuePair("ten", pTenBan));
				p.add(new BasicNameValuePair("ketqua", pKetQua));
				p.add(new BasicNameValuePair("noidung", pBinhLuan));
				JSONParser jParser = new JSONParser();
				String url = Variables.linkDuDoan;
				JSONObject json = jParser.makeHttpRequest(url, "GET", p);

				try {
					checkSuccess = json.getString("success");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				if (checkSuccess.equals("1")) {
					// strVungNgam = "Ket noi thanh cong";
					// strVungNgam = json.toString();

					try {
						JSONArray products = json.getJSONArray("message");
						// mangTen, mangKetqua, mangNoidung, mangThoigian
						mangTen = new String[products.length()];
						mangKetqua = new String[products.length()];
						mangNoidung = new String[products.length()];
						mangThoigian = new String[products.length()];
						for (int j = 0; j < products.length(); j++) {
							JSONObject c = products.getJSONObject(j);
							// Lay ten, ketqua, noidung, created_at
							mangTen[j] = c.getString("ten");
							mangKetqua[j] = c.getString("ketqua");
							mangNoidung[j] = c.getString("noidung");
							mangThoigian[j] = c.getString("created_at");

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
			// tvServerTraVeDuDoan.setText(strVungNgam);

			if (checkSuccess.equals("1")) {

				ArrayList<DuDoanItemContent> array = new ArrayList<DuDoanItemContent>();
				for (int i = 0; i < mangTen.length; i++) {
					array.add(new DuDoanItemContent(mangTen[i], mangKetqua[i],
							mangNoidung[i], mangThoigian[i]));
				}

				adapter = new DuDoanMyAdapter(DuDoanActivity.this,
						R.layout.du_doan_row_item, array);
				lvDuDoan.setAdapter(adapter);
				// Khong cho nhapten nua
				if (etTenBan.getText().toString().length() > 0) {
					etTenBan.setFocusable(false);
				}
				// etKetQua.setFocusable(false);
				etKetQua.setText("");
				etBinhLuan.setText("");
			}
			super.onPostExecute(result);
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
