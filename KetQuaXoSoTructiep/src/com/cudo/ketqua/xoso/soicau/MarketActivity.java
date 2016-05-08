package com.cudo.ketqua.xoso.soicau;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MarketActivity extends ActionBarActivity {

	private int iView = 0;
	private WebView webview;
	//http://play.android.vn/?color=87989
	private static String link = "http://play.android.vn";
	private static String link2 = "http://play.android.vn/?color=87989";
	private ProgressDialog dialog;
	int i=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_market);

		webview = (WebView) findViewById(R.id.wvNews);
		webview.getSettings().setSupportZoom(true);
		webview.setInitialScale(1);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setUseWideViewPort(true);
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webview.setScrollbarFadingEnabled(true);
		webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(link2);
		webview.setWebViewClient(new MaoWebViewClient());
		
//		try {
//			String url = "https://dl.dropboxusercontent.com/u/119735704/API/store/link.txt";
//			//link = HttpURLConnection.sendGet(url);
//			AsyncHttpClient client = new AsyncHttpClient();
//			client.get(url, new AsyncHttpResponseHandler() {
//
//				@Override
//				public void onSuccess(int arg0, String request) {
//					super.onSuccess(arg0, request);
//					link = request;
//					tvURL.setText(link);
//					webview.loadUrl(link);
//					webview.setWebViewClient(new MaoWebViewClient());
////					if (dialog != null) {
////						dialog.dismiss();
////					}
//				}
//
//				@Override
//				protected void sendFailureMessage(Throwable arg0, String arg1) {
//					super.sendFailureMessage(arg0, arg1);
//				}
//
//			});
//
//		} catch (Exception e) {
//		}
		


	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		try {
			if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
				webview.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
			
		} catch (Exception e) {
		}
		return true;
	}


	class MaoWebViewClient extends WebViewClient {

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			if (iView == 0) {
				dialog = new ProgressDialog(MarketActivity.this);
				dialog.setMessage("Please wait...");
				dialog.setCancelable(true);
				dialog.show();
				iView = 1;
			}
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if (dialog != null) {
						dialog.dismiss();
					}
				}
			}, 3000);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (dialog != null) {
				dialog.dismiss();
			}
		}

	}
	
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
