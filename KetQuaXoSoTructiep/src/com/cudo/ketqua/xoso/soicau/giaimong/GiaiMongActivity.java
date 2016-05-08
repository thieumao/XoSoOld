package com.cudo.ketqua.xoso.soicau.giaimong;

import java.io.IOException;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.dudoan.DuDoanActivity;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.startapp.android.publish.StartAppAd;

public class GiaiMongActivity extends ActionBarActivity implements
		OnItemClickListener {

	EditText search_edit;
	ListView listView;
	Cursor c = null;
	private static String TABLE_NAME = "Dream";
	private TextView tvChu;
	TextView tvSo;
	CheckBox cbChon;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_giai_mong);
		
		try {
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);
			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {
		}

		try {
			this.getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		} catch (Exception e) {
		}
		

		listView = (ListView) findViewById(R.id.my_list);
		listView.setOnItemClickListener(this);

		search_edit = (EditText) findViewById(R.id.search_edit);
		DanhSach(" ");
		search_edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = search_edit.getText().toString();
				if (str.length() > 0) {
					DanhSach(str);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		tvChu = (TextView) v.getTag(R.id.tvChu);
		tvSo = (TextView) v.getTag(R.id.tvSo);
		cbChon = (CheckBox) v.getTag(R.id.cbChon);
		// Toast.makeText(
		// v.getContext(),
		// tvChu.getText().toString() + " --> "
		// + tvSo.getText().toString() + " "
		// + isCheckedOrNot(cbChon), Toast.LENGTH_LONG).show();
		// Chia se giac mo

		Context context = this;
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.chia_se_giac_mo, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Chia sẻ",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								String strTen = userInput.getText().toString();
								String strSo = tvSo.getText().toString();
								String strChu = tvChu.getText().toString();
								// String stringChiaSe = tvChu.getText()
								// .toString()
								// + " --> "
								// + tvSo.getText().toString();
								// Qua SMS
								// Uri uri = Uri.parse("smsto:" + strSDT);
								// Intent intent = new Intent(
								// Intent.ACTION_SENDTO, uri);
								// intent.putExtra("sms_body", stringChiaSe);
								// startActivity(intent);
								// Qua Share
								// Intent intent = new
								// Intent(Intent.ACTION_SEND);
								// intent.setType("text/plain");
								// intent.putExtra(Intent.EXTRA_TEXT,
								// stringChiaSe);
								// startActivity(intent);
								Intent intent = new Intent(
										GiaiMongActivity.this,
										DuDoanActivity.class);
								intent.putExtra("strTen", strTen);
								intent.putExtra("strSo", strSo);
								intent.putExtra("strChu", strChu);
								// intent.putExtra("tenTinh", tenTinh);
								startActivity(intent);
							}
						})
				.setNegativeButton("Hủy",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	// private String isCheckedOrNot(CheckBox checkbox) {
	// if (checkbox.isChecked())
	// return " --> v ";
	// else
	// return " --> x ";
	// }

	public void DanhSach(String strInput) {
		GiaiMongDatabaseHelper myDbHelper = new GiaiMongDatabaseHelper(
				GiaiMongActivity.this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
		c = myDbHelper.query(TABLE_NAME, null, null, null, null, null, null);
		ArrayList<GiaiMongModel> list = new ArrayList<GiaiMongModel>();
		if (c.moveToFirst()) {
			do {
				if ((c.getString(0).toUpperCase().contains(strInput
						.toUpperCase()))
						|| (c.getString(3).toUpperCase().contains(strInput
								.toUpperCase()))) {
					list.add(new GiaiMongModel(c.getString(0), c.getString(1)));
				}
			} while (c.moveToNext());
		}

		ArrayAdapter<GiaiMongModel> adapter = new GiaiMongMyAdapter(this, list);
		listView.setAdapter(adapter);
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