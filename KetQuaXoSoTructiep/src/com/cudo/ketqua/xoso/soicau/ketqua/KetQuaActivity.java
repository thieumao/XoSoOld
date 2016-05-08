package com.cudo.ketqua.xoso.soicau.ketqua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.JSONParser;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.startapp.android.publish.StartAppAd;

public class KetQuaActivity extends ActionBarActivity {

	private ProgressDialog dialog;
	private String[] mangKetQua;
	int vitriBaMien;

	KetQuaExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ket_qua);

		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}

		// try {
		// // Startapp banner
		// StartAppSearch.init(this, "104144450", "203881518");
		// StartAppSearch.showSearchBox(this);
		// StartAppAd.init(this, "104144450", "203881518");
		// } catch (Exception e) {
		// }

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new KetQuaExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " mở",
				// Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " đóng",
				// Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// Toast.makeText(
				// getApplicationContext(),
				// listDataHeader.get(groupPosition)
				// + " : "
				// + listDataChild.get(
				// listDataHeader.get(groupPosition)).get(
				// childPosition), Toast.LENGTH_SHORT)
				// .show();
				String strVung = listDataChild.get(
						listDataHeader.get(groupPosition)).get(childPosition);
				for (int i = 0; i < Variables.TinhCaBaMien.length; i++) {
					if (strVung.equals(Variables.TinhCaBaMien[i])) {
						vitriBaMien = i;
						if (KiemTraMang(getApplicationContext())) {
							new ChayNgam().execute("");
						} else {
							Toast.makeText(getApplicationContext(),
									"Hãy kiểm tra lại Wifi/3G",
									Toast.LENGTH_SHORT).show();
						}
					}
				}

				// Intent intent;
				// if (strVung.equals(Variables.TinhMienBac[0])) {
				// // intent = new Intent(KetQuaActivity.this,
				// // KetQuaMienBac.class);
				// // startActivity(intent);
				// new ChayNgam().execute("");
				// } else {
				// // intent = new Intent(KetQuaActivity.this,
				// // KetQuaMienKhac.class);
				// // startActivity(intent);
				// // new ChayNgamMienKhac().execute("");
				// }
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		for (int i = 0; i < Variables.XoSoBaMien.length; i++) {
			listDataHeader.add(Variables.XoSoBaMien[i]);
		}

		// Adding child data
		List<String> listXSMB = new ArrayList<String>();
		for (int i = 0; i < Variables.TinhMienBac.length; i++) {
			listXSMB.add(Variables.TinhMienBac[i]);
		}

		List<String> listXSMT = new ArrayList<String>();
		for (int i = 0; i < Variables.TinhMienTrung.length; i++) {
			listXSMT.add(Variables.TinhMienTrung[i]);
		}

		List<String> listXSMN = new ArrayList<String>();
		for (int i = 0; i < Variables.TinhMienNam.length; i++) {
			listXSMN.add(Variables.TinhMienNam[i]);
		}

		listDataChild.put(listDataHeader.get(0), listXSMB);
		listDataChild.put(listDataHeader.get(1), listXSMT);
		listDataChild.put(listDataHeader.get(2), listXSMN);
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
			dialog = new ProgressDialog(KetQuaActivity.this);
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
				Toast.makeText(KetQuaActivity.this, "Hãy kiểm tra lại Wifi/3G",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			Intent intent = new Intent(KetQuaActivity.this, KetQuaChiTiet.class);
			intent.putExtra("mKQ", mangKetQua);
			intent.putExtra("vitriBaMien", vitriBaMien);
			startActivity(intent);
			// if (vitriBaMien == 0) {
			// intent = new Intent(KetQuaActivity.this, KetQuaChiTiet.class);
			// intent.putExtra("mKQ", mangKetQua);
			// // intent.putExtra("tenTinh", tenTinh);
			// startActivity(intent);
			// } else {
			// // intent = new Intent(KetQuaActivity.this,
			// // KetQuaMienKhac.class);
			// // intent.putExtra("mKQ", mangKetQua);
			// // // intent.putExtra("tenTinh", tenTinh);
			// // startActivity(intent);
			// }
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

	// @Override
	// public boolean onMenuItemSelected(int featureId, MenuItem item) {
	//
	// int itemId = item.getItemId();
	//
	// if (itemId == android.R.id.home) {
	// // Do stuff
	// }
	// return true;
	// }

}
