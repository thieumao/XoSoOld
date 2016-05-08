package com.cudo.ketqua.xoso.soicau;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cudo.ketqua.xoso.soicau.dudoan.DuDoanActivity;
import com.cudo.ketqua.xoso.soicau.giaimong.GiaiMongActivity;
import com.cudo.ketqua.xoso.soicau.homnay.HomNayActivity;
import com.cudo.ketqua.xoso.soicau.ketqua.KetQuaActivity;
import com.cudo.ketqua.xoso.soicau.phantich.PhanTichActivity;
import com.cudo.ketqua.xoso.soicau.quaythu.QuayThuActivity;
import com.cudo.ketqua.xoso.soicau.soicau.SoiCauActivity;
import com.cudo.ketqua.xoso.soicau.thongke.ThongKeActivity;
import com.cudo.ketqua.xoso.soicau.utils.Variables;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppAd.AdMode;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button btKetQua, btHomNay, btGiaiMong, btDuDoan, btPhanTich,
			btThongKe, btSoiCau, btQuayThu;

	private StartAppAd startAppAd = new StartAppAd(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			updateVersion();
			showAds();
			// StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
			// Variables.STARTAPP_APP_ID);
			// StartAppAd.showSlider(this);
			// Quang Cao StartAPp
			// StartAppAd.init(this, "DeveloperID", "ApplicationID");
			StartAppAd.init(this, Variables.STARTAPP_DEV_ID,
					Variables.STARTAPP_APP_ID);

			// String appname = this.getString(R.string.app_name);
			/** Create Splash Ad **/
			StartAppAd.showSplash(
					this,
					savedInstanceState,
					new SplashConfig().setTheme(Theme.SKY)
							.setLogo(R.drawable.ketquaxoso)
							.setAppName(this.getString(R.string.app_name)));

			/** Add Slider **/
			StartAppAd.showSlider(this);
		} catch (Exception e) {

		}

		btKetQua = (Button) findViewById(R.id.buttonKetQua);
		btHomNay = (Button) findViewById(R.id.buttonHomNay);
		btGiaiMong = (Button) findViewById(R.id.buttonGiaiMong);
		btDuDoan = (Button) findViewById(R.id.buttonDuDoan);
		btPhanTich = (Button) findViewById(R.id.buttonPhanTich);
		btThongKe = (Button) findViewById(R.id.buttonThongKe);
		btSoiCau = (Button) findViewById(R.id.buttonSoiCau);
		btQuayThu = (Button) findViewById(R.id.buttonQuayThu);

		btKetQua.setOnClickListener(this);
		btHomNay.setOnClickListener(this);
		btGiaiMong.setOnClickListener(this);
		btDuDoan.setOnClickListener(this);
		btPhanTich.setOnClickListener(this);
		btThongKe.setOnClickListener(this);
		btSoiCau.setOnClickListener(this);
		btQuayThu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// Show ads Startapp
		Random rd = new Random();
		int Max = 1, Min = 5;
		int iRandom =  rd.nextInt((Max - Min + 1) + Min);
		if (iRandom == 1){
			startAppAd.showAd(); // show the ad
			startAppAd.loadAd(AdMode.OVERLAY);
		}
		
		// Lam viec binh thuong
		Intent intent;
		switch (v.getId()) {
		case R.id.buttonKetQua:
			intent = new Intent(this, KetQuaActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonHomNay:
			intent = new Intent(this, HomNayActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonGiaiMong:
			intent = new Intent(this, GiaiMongActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonDuDoan:
			intent = new Intent(this, DuDoanActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonPhanTich:
			intent = new Intent(this, PhanTichActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonThongKe:
			intent = new Intent(this, ThongKeActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonSoiCau:
			intent = new Intent(this, SoiCauActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonQuayThu:
			intent = new Intent(this, QuayThuActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportActionBar().setHomeButtonEnabled(true);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == android.R.id.home) {
			// Intent intent = new Intent(this, MainActivity.class);
			// startActivity(intent);
//			finish();
			quitGame();
		}
		// if (item.getItemId() == R.id.action_about) {
		// Toast.makeText(MainActivity.this,
		// "Tác giả: Thiệu Mao\n Email: thieumao@gmail.com",
		// Toast.LENGTH_SHORT).show();
		// }
		if (item.getItemId() == R.id.action_mail) {
			try {
				Intent gmail = new Intent(Intent.ACTION_VIEW);
				gmail.setClassName("com.google.android.gm",
						"com.google.android.gm.ComposeActivityGmail");
				gmail.putExtra(Intent.EXTRA_EMAIL,
						new String[] { "thieumao@gmail.com" });
				gmail.setData(Uri.parse("thieumao@gmail.com"));
				gmail.putExtra(Intent.EXTRA_SUBJECT,
						"Thắc mắc/Góp ý App Xổ Số Việt Nam");
				gmail.setType("plain/text");
				gmail.putExtra(Intent.EXTRA_TEXT, "...");
				startActivity(gmail);
			} catch (Exception e) {
			}
		}
		if (item.getItemId() == R.id.action_gamepad) {
			// Toast.makeText(MainActivity.this,
			// "Go roi " + item.getTitle().toString(), Toast.LENGTH_SHORT)
			// .show();
			// Intent intent = new Intent(Intent.ACTION_SEND);
			// intent.setType("text/plain");
			// intent.putExtra(
			// Intent.EXTRA_TEXT,
			// "Ứng dụng xem kết quả Xố Số Việt Nam miễn phí: https://play.google.com/store/apps/details?id=com.xoso.vietnam");
			// startActivity(intent);
			if (KiemTraMang(getApplicationContext())) {
				try {
					// Intent intent = new Intent(this, MarketActivity.class);
					// startActivity(intent);
					// startActivity(new Intent(
					// Intent.ACTION_VIEW,
					// Uri.parse("market://details?id=" + getPackageName())));
					startActivity(new Intent(
							Intent.ACTION_VIEW,
							Uri.parse("https://play.google.com/store/apps/developer?id="
									+ Variables.DEV_ID)));
				} catch (Exception e) {
				}
			} else {
				Toast.makeText(this, "Hãy kiểm tra lại Wifi/3G",
						Toast.LENGTH_SHORT).show();
			}
		}
		return super.onOptionsItemSelected(item);
	}

	// Quảng Cáo
	@Override
	public void onResume() {
		super.onResume();
		startAppAd.onResume();
	}

	// Quảng Cáo
	@Override
	public void onBackPressed() {
		startAppAd.onBackPressed();
		super.onBackPressed();
	}

	// //////////////
	// ////////////////////////////////////////////////////////////////////////////////

	// update version
	private void updateVersion() {
		AsyncHttpClient client = new AsyncHttpClient();
		String url = Variables.linkUpdateVersion;

		client.get(url, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, String request) {
				super.onSuccess(arg0, request);
				// MyLog.E("update" + request);
				JSONObject jsonObj;
				try {
					jsonObj = new JSONObject(request);
					String LinkDownload = "", versionName = "";
					int versionCode = -1;
					if (jsonObj != null) {
						LinkDownload = jsonObj.getString("LinkDownload");
						versionCode = jsonObj.getInt("versionCode");
						versionName = jsonObj.getString("versionName");
						if (versionCode > getVersionApp()) // show dialog
							showDialogUpdate(versionName, LinkDownload);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void sendFailureMessage(Throwable arg0, String arg1) {
				super.sendFailureMessage(arg0, arg1);
			}

		});
	}

	// get version code
	public int getVersionApp() {
		int v = 0;
		try {
			v = this.getPackageManager().getPackageInfo(this.getPackageName(),
					0).versionCode;
		} catch (NameNotFoundException e) {
			// Huh? Really?
		}
		return v;
	}

	// show dialog update
	public void showDialogUpdate(String versionName, final String urldownload) {
		AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
		editDialog.setTitle(getResources().getString(
				R.string.dialog_update_title));
		editDialog.setMessage(String.format(
				getResources().getString(R.string.dialog_update_content),
				versionName));
		editDialog.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(Intent.ACTION_VIEW, Uri
								.parse(urldownload));
						startActivity(i);
					}
				});

		editDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		editDialog.show();
	}

	// show ads
	// update version
	private void showAds() {
		AsyncHttpClient client = new AsyncHttpClient();
		String url = Variables.linkADS;

		client.get(url, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, String request) {
				super.onSuccess(arg0, request);
				// MyLog.E("show ads" + request);
				JSONObject jsonObj;
				try {
					jsonObj = new JSONObject(request);
					String LinkDownload = "", ShowAds = "", Content = "", Avatar = "";
					// int versionCode = -1;
					if (jsonObj != null) {
						LinkDownload = jsonObj.getString("LinkDownload");
						ShowAds = jsonObj.getString("ShowAds");
						Content = jsonObj.getString("Content");
						Avatar = jsonObj.getString("Avatar");
						// Log.e("avatar>>>>","url avatar >>>" + Avatar);
						if (ShowAds.contentEquals("true")) // show dialog
							showDialogAds(LinkDownload, Content, Avatar);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void sendFailureMessage(Throwable arg0, String arg1) {
				super.sendFailureMessage(arg0, arg1);
				// MyLog.E("show ads false" + arg1);
			}

		});
	}

	private void showDialogAds(String download, String content, String avatar) {
		com.cudo.ketqua.xoso.soicau.ads.AdsDialog dialog = new com.cudo.ketqua.xoso.soicau.ads.AdsDialog(
				this, R.style.Dialog_NoTitle, download, content, avatar);
		dialog.show();

	}

	// //////

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
	
	// Khi thoat app
	public void quitGame() {
		AlertDialog.Builder editDialog = new AlertDialog.Builder(this);
		editDialog.setTitle("Thoát");
		editDialog.setMessage("Bạn có muốn thoát ứng dụng lúc này không?");
		editDialog.setIcon(R.drawable.ketquaxoso50);

		editDialog.setPositiveButton("Thoát",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// showInterstitialAd();
						// adBuddiz.showAd();
						finish();
					}
				});

		editDialog.setNegativeButton("Đánh giá",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("https://play.google.com/store/apps/details?id="
										+ getPackageName()));
						//Log.e(">>>>package>>>", getPackageName());
						startActivity(i);
					}
				});

		editDialog.setNeutralButton("Bỏ qua",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		editDialog.show();
	}
}
