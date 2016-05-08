package com.cudo.ketqua.xoso.soicau.ketqua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.fragments.FragmentKetQuaChiTiet;
import com.cudo.ketqua.xoso.soicau.utils.JSONParser;
import com.cudo.ketqua.xoso.soicau.utils.Variables;

//FragmentActivity
public class KetQuaChiTiet extends ActionBarActivity {

	int NUM_PAGES = Variables.soTrangKQ;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	private String[] mangKetQua;

	private ProgressDialog dialog;
	// private String[] mangKetQua;
	int vitriBaMien = 0;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ket_qua_chi_tiet);

		bundle = getIntent().getExtras();
		mangKetQua = bundle.getStringArray("mKQ");
		if (vitriBaMien == 0) {
			vitriBaMien = bundle.getInt("vitriBaMien");
		}

		mPager = (ViewPager) findViewById(R.id.view_pager);
		mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(Variables.soTrangKQ - 1);

		// TextView test = (TextView) findViewById(R.id.test);
		// test.setText(mangKetQua[0]);

	}

	private class ViewPagerAdapter extends FragmentStatePagerAdapter {
		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new FragmentKetQuaChiTiet();
			Bundle bun = new Bundle();
			bun.putInt("stt", position);
			bun.putStringArray("mKQ", mangKetQua);
			fragment.setArguments(bun);
			return fragment;
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}

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

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(KetQuaChiTiet.this);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params2) {
			if (KiemTraMang(getApplicationContext())) {
				// Mang luu du lieu
				String[] arrResult = new String[Variables.soTrangKQ];

				String url = Variables.linkSoiCau;
				JSONParser jParser = new JSONParser();
				List<NameValuePair> p = new ArrayList<NameValuePair>();
				String strLimit = String.valueOf(Variables.soTrangKQ);
				p.add(new BasicNameValuePair("limit", strLimit));
				// Thu Mien Bac
				p.add(new BasicNameValuePair("vung",
						Variables.TinhCaBaMien_LINKS[vitriBaMien]));
				JSONObject json = jParser.makeHttpRequest(url, "GET", p);
				Log.d("Kiem tra json ", json.toString());
				try {
					JSONArray products = json.getJSONArray("message");
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						String ketqua = c.getString("ketqua");
						String thoigian = c.getString("thoigian");
						// Log.d("Ket qua: ", ketqua);
						// Log.d("Thoi gian: ", thoigian);
						ketqua = ketqua.replace(" ", "");
						thoigian = thoigian.replace(" ", "");
						String strResult = vitriBaMien + ";" + thoigian + ";"
								+ ketqua;
						arrResult[i] = strResult;
					}
					mangKetQua = arrResult;
					// Dao nguoc chuoi mangKetQua
					List<String> list = Arrays.asList(mangKetQua);
					Collections.reverse(list);
					mangKetQua = (String[]) list.toArray();
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Toast.makeText(KetQuaChiTiet.this, "Hãy kiểm tra lại Wifi/3G",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			finish();
			Intent intent = new Intent(KetQuaChiTiet.this, KetQuaChiTiet.class);
			intent.putExtra("mKQ", mangKetQua);
			intent.putExtra("vitriBaMien", vitriBaMien);
			startActivity(intent);
			super.onPostExecute(result);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.ket_qua_chi_tiet, menu);
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
			// String strmPager = "Trang " + mPager.getCurrentItem();
			// Toast.makeText(KetQuaChiTiet.this,
			// strmPager + " - " + item.getTitle().toString(),
			// Toast.LENGTH_SHORT).show();
			// mPager.setCurrentItem(99);
			if (KiemTraMang(getApplicationContext())) {
				new ChayNgam().execute("");
			} else {
				Toast.makeText(getApplicationContext(),
						"Hãy kiểm tra lại Wifi/3G", Toast.LENGTH_SHORT).show();
			}
		}
		if (item.getItemId() == R.id.action_share) {
			int stt = mPager.getCurrentItem();
			;
			String[] catMangKetQua = mangKetQua[stt].split(";");
			String vung = catMangKetQua[0];
			int iTinh = Integer.valueOf(vung);
			vung = "Xổ Số " + Variables.TinhCaBaMien[iTinh];
			String thoigian = catMangKetQua[1];
			String ketqua = catMangKetQua[2];
			ketqua = ketqua.replace(" ", "");
			String[] catKetQua = ketqua.split(",");
			// String strShare = "Kết quả " + vung + " ngày " + thoigian
			// + ": đặc biệt " + catKetQua[0] + ", nhất to: "
			// + catKetQua[1];
			// Toast.makeText(KetQuaChiTiet.this, strShare, Toast.LENGTH_SHORT)
			// .show();
			String[] mangGiai = new String[9];
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

			String strShare = "Kết quả " + vung + " ngày " + thoigian
					+ ": đặc biệt " + mangGiai[0] + ", nhất to: " + mangGiai[1];

			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, strShare);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
