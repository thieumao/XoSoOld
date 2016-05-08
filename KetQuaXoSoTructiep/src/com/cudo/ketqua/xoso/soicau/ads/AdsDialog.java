package com.cudo.ketqua.xoso.soicau.ads;

import com.cudo.ketqua.xoso.soicau.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdsDialog extends Dialog implements View.OnClickListener{
	
	private Context _content;
	private String mLinkDownload;
	private String mContent;
	private String mAvatar;
	public AdsDialog(Context context, int theme, String mLinkDownload, String mContent, String mAvatar) {		
		super(context,theme);
		initialView(context,mLinkDownload, mContent, mAvatar);
	}
	public void initialView(Context context, String mLinkDownload, String mContent, String mAvatar){
		_content=context;
		setContentView(R.layout.adsdialog);
		Button btn=(Button) findViewById(R.id.btn_Later);
		btn.setOnClickListener(this);
		btn=(Button) findViewById(R.id.btn_Rate);
		btn.setOnClickListener(this);
		
		this.mLinkDownload = mLinkDownload;
		this.mContent = mContent;
		this.mAvatar = mAvatar;
		
		ImageView imgAvatar = (ImageView) findViewById(R.id.imageAvatar);
		TextView tvContent = (TextView) findViewById(R.id.text_dialog_rate);
		tvContent.setText(this.mContent);
//		UrlImageViewHelper.setUrlDrawable(imgAvatar, this.mAvatar, R.drawable.logo1);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_Later:
			dismiss();
			break;
		case R.id.btn_Rate:
			dismiss();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(mLinkDownload));
			(_content).startActivity(intent);
			break;
		default:
			break;
		}
	}

}
