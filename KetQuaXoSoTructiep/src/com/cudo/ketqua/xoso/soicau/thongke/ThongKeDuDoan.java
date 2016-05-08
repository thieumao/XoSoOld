package com.cudo.ketqua.xoso.soicau.thongke;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.JSONParser;
import com.cudo.ketqua.xoso.soicau.utils.Variables;

public class ThongKeDuDoan extends ActionBarActivity {

	ThongKeMyAdapter adapter;
	ArrayList<ThongKeItemContent> items;
	ListView lvMain;

	private ProgressDialog dialog;

	private EditText etNgay;
	private Button btTkDuDoan;
	private TextView tvServerTraVeDuDoan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_ke_du_doan);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		etNgay = (EditText) findViewById(R.id.editTextNgayDuDoan);
		btTkDuDoan = (Button) findViewById(R.id.buttonThongKeDuDoan);
		tvServerTraVeDuDoan = (TextView) findViewById(R.id.tvServerTraVeDuDoan);
		lvMain = (ListView) findViewById(R.id.lvMainDuDoan);

		btTkDuDoan.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				hideSoftKeyboard(ThongKeDuDoan.this);
				if (KiemTraMang(getApplicationContext())) {
					new ChayNgam().execute("");
				} else {
					Toast.makeText(ThongKeDuDoan.this,
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

	class ChayNgam extends AsyncTask<String, Void, Void> {

		private String strVungNgam = "Không tìm thấy kết quả";
		private String checkSuccess = "0";
		private String[] catDayso;
		private String[] catDayxuathien;
		private String[] catDayphantram;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(ThongKeDuDoan.this);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params2) {
			if (KiemTraMang(getApplicationContext())) {
				// Lay Lan Quay
				String pNgay = etNgay.getText().toString();
				String url = Variables.linkThongKeDuDoan;
				JSONParser jParser = new JSONParser();
				List<NameValuePair> p = new ArrayList<NameValuePair>();
				// vung=xo-so-kon-tum&limit=70&loai=haicua
				p.add(new BasicNameValuePair("limit", pNgay));

				JSONObject json = jParser.makeHttpRequest(url, "GET", p);

				try {
					checkSuccess = json.getString("success");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				if (checkSuccess.equals("1")) {
					strVungNgam = "Kết quả thống kê trong " + pNgay
							+ " lần dự đoán.";

					try {
						JSONArray products = json.getJSONArray("message");
						for (int j = 0; j < products.length(); j++) {
							JSONObject c = products.getJSONObject(j);
							// Lay So
							String strDayso = c.getString("dayso");
							strDayso = strDayso.replace(" ", "");
							catDayso = strDayso.split(",");
							// Lay Xuat Hien
							String strXuathien = c.getString("dayxuathien");
							strXuathien = strXuathien.replace(" ", "");
							catDayxuathien = strXuathien.split(",");
							// Lay Phan Tram
							String strDayphantram = c.getString("dayphantram");
							strDayphantram = strDayphantram.replace(" ", "");
							catDayphantram = strDayphantram.split(",");
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
			tvServerTraVeDuDoan.setText(strVungNgam);

			if (checkSuccess.equals("1")) {

				ArrayList<ThongKeItemContent> array = new ArrayList<ThongKeItemContent>();
				for (int i = 0; i < catDayso.length; i++) {
					array.add(new ThongKeItemContent(catDayso[i],
							catDayxuathien[i], catDayphantram[i] + "%"));
				}

				adapter = new ThongKeMyAdapter(ThongKeDuDoan.this,
						R.layout.thong_ke_row_item, array);
				lvMain.setAdapter(adapter);
			}
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
