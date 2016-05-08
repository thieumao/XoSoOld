package com.cudo.ketqua.xoso.soicau;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MarketActivity2 extends Activity {

	// TextView tvURL;
	private int iView = 0;
	private WebView webview;
	private String link = "http://play.android.vn/?color=87989";
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_market);

		// tvURL = (TextView) findViewById(R.id.tvURL);

		try {
			String url = "https://dl.dropboxusercontent.com/u/119735704/API/store/link.txt";
			// String strUrl = HttpURLConnection.sendGet(url);
			link = HttpURLConnection.sendGet(url);
			// tvURL.setText(strUrl);

		} catch (Exception e) {
		}

		webview = (WebView) findViewById(R.id.wvNews);
		webview.getSettings().setSupportZoom(true);
		webview.setInitialScale(1);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setUseWideViewPort(true);
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webview.setScrollbarFadingEnabled(true);
		webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		webview.getSettings().setJavaScriptEnabled(true);

		webview.loadUrl(link);
		webview.setWebViewClient(new MaoWebViewClient());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
			webview.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		// tvURL.setText("Trong menu");
		return true;
	}

	class MaoWebViewClient extends WebViewClient {

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			if (iView == 0) {
				dialog = new ProgressDialog(MarketActivity2.this);
				dialog.setMessage("Loading...");
				dialog.setCancelable(true);
				dialog.show();
				iView = 1;
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						if (dialog != null) {
							dialog.dismiss();
						}
					}
				}, 200);
			}
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (dialog != null) {
				dialog.dismiss();
			}
		}

	}

}
