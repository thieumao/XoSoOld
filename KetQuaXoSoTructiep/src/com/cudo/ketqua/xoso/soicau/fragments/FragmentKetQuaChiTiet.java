package com.cudo.ketqua.xoso.soicau.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cudo.ketqua.xoso.soicau.R;
import com.cudo.ketqua.xoso.soicau.utils.Variables;

public class FragmentKetQuaChiTiet extends Fragment {

	private TextView tvVung, tvThoiGian, tvkqdb, tvkq1, tvkq2, tvkq3, tvkq4,
			tvkq5, tvkq6, tvkq7, tvkq8, dau0, dau1, dau2, dau3, dau4, dau5,
			dau6, dau7, dau8, dau9, duoi0, duoi1, duoi2, duoi3, duoi4, duoi5,
			duoi6, duoi7, duoi8, duoi9, chuGiaiTam;
	private int stt;
	private String[] mangKetQua;
//	private String tenTinh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get parameter.
		Bundle bun = getArguments();
		stt = bun.getInt("stt");
		mangKetQua = bun.getStringArray("mKQ");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ket_qua_chi_tiet_fragment,
				container, false);
		String[] catMangKetQua = mangKetQua[stt].split(";");
		String vung = catMangKetQua[0];
		int iTinh = Integer.valueOf(vung);
		vung = "Xổ Số " + Variables.TinhCaBaMien[iTinh];
		String thoigian = catMangKetQua[1];
		// String ketqua = catMangKetQua[2];
		// ketqua = ketqua.replace(" ", "");
		// String[] mangGiai = ketqua.split(",");
		String ketqua = catMangKetQua[2];
		ketqua = ketqua.replace(" ", "");
		String[] catKetQua = ketqua.split(",");
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

		// tvFA = (TextView) view.findViewById(R.id.tvFA);
		// String test = vung + " " + thoigian + " " + ketqua;
		// tvFA.setText(test);

		tvVung = (TextView) view.findViewById(R.id.tvVung);
		tvThoiGian = (TextView) view.findViewById(R.id.tvThoiGian);
		tvkqdb = (TextView) view.findViewById(R.id.tvkqdb);
		tvkq1 = (TextView) view.findViewById(R.id.tvkq1);
		tvkq2 = (TextView) view.findViewById(R.id.tvkq2);
		tvkq3 = (TextView) view.findViewById(R.id.tvkq3);
		tvkq4 = (TextView) view.findViewById(R.id.tvkq4);
		tvkq5 = (TextView) view.findViewById(R.id.tvkq5);
		tvkq6 = (TextView) view.findViewById(R.id.tvkq6);
		tvkq7 = (TextView) view.findViewById(R.id.tvkq7);
		tvkq8 = (TextView) view.findViewById(R.id.tvkq8);
		dau0 = (TextView) view.findViewById(R.id.dau0);
		dau1 = (TextView) view.findViewById(R.id.dau1);
		dau2 = (TextView) view.findViewById(R.id.dau2);
		dau3 = (TextView) view.findViewById(R.id.dau3);
		dau4 = (TextView) view.findViewById(R.id.dau4);
		dau5 = (TextView) view.findViewById(R.id.dau5);
		dau6 = (TextView) view.findViewById(R.id.dau6);
		dau7 = (TextView) view.findViewById(R.id.dau7);
		dau8 = (TextView) view.findViewById(R.id.dau8);
		dau9 = (TextView) view.findViewById(R.id.dau9);
		duoi0 = (TextView) view.findViewById(R.id.duoi0);
		duoi1 = (TextView) view.findViewById(R.id.duoi1);
		duoi2 = (TextView) view.findViewById(R.id.duoi2);
		duoi3 = (TextView) view.findViewById(R.id.duoi3);
		duoi4 = (TextView) view.findViewById(R.id.duoi4);
		duoi5 = (TextView) view.findViewById(R.id.duoi5);
		duoi6 = (TextView) view.findViewById(R.id.duoi6);
		duoi7 = (TextView) view.findViewById(R.id.duoi7);
		duoi8 = (TextView) view.findViewById(R.id.duoi8);
		duoi9 = (TextView) view.findViewById(R.id.duoi9);
		chuGiaiTam = (TextView) view.findViewById(R.id.chuGiaiTam);

		tvVung.setText(vung);
		tvThoiGian.setText(thoigian);
		tvkqdb.setText(mangGiai[0].replace("-", " "));
		tvkq1.setText(mangGiai[1].replace("-", " "));
		tvkq2.setText(mangGiai[2].replace("-", " "));
		tvkq3.setText(mangGiai[3].replace("-", " "));
		tvkq4.setText(mangGiai[4].replace("-", " "));
		tvkq5.setText(mangGiai[5].replace("-", " "));
		tvkq6.setText(mangGiai[6].replace("-", " "));
		tvkq7.setText(mangGiai[7].replace("-", " "));

		if (iTinh > 0) {
			chuGiaiTam.setText("Giải Tám");
			tvkq8.setText(mangGiai[8].replace("-", " "));
			if ((ketqua.length() >= 98) && (ketqua.length() <= 99)) {
				ThongKe();
			}
		} else {
			if ((ketqua.length() >= 133) && (ketqua.length() <= 134)) {
				ThongKe();
			}
		}

		return view;
	}

	public void ThongKe() {
		String strThongKe = tvkqdb.getText().toString().trim() + " "
				+ tvkq1.getText().toString().trim() + " "
				+ tvkq2.getText().toString().trim() + " "
				+ tvkq3.getText().toString().trim() + " "
				+ tvkq4.getText().toString().trim() + " "
				+ tvkq5.getText().toString().trim() + " "
				+ tvkq6.getText().toString().trim() + " "
				+ tvkq7.getText().toString().trim() + " "
				+ tvkq8.getText().toString().trim();
		strThongKe = strThongKe.replace("  ", " ");
		strThongKe = strThongKe.trim();
		Log.v("thong ke", strThongKe);
		String[] mangThongKe = strThongKe.split(" ");
		String[] mang2TK = new String[mangThongKe.length];
		String[] dau = new String[mangThongKe.length];
		String[] duoi = new String[mangThongKe.length];
		for (int i = 0; i < mangThongKe.length; i++) {
			String s = mangThongKe[i];
			int l = s.length();
			mang2TK[i] = s.substring(l - 2, l);
			dau[i] = s.substring(l - 2, l - 1);
			duoi[i] = s.substring(l - 1, l);
			Log.v("So " + i, mang2TK[i] + "");
		}

		String[] sodau = new String[10];
		String[] soduoi = new String[10];
		for (int i = 0; i <= 9; i++) {
			sodau[i] = "";
			soduoi[i] = "";
		}
		for (int i = 0; i < mangThongKe.length; i++) {
			int da = Integer.valueOf(dau[i]);
			sodau[da] = sodau[da] + " " + duoi[i];
			int du = Integer.valueOf(duoi[i]);
			soduoi[du] = soduoi[du] + " " + dau[i];
		}

		dau0.setText(sodau[0]);
		dau1.setText(sodau[1]);
		dau2.setText(sodau[2]);
		dau3.setText(sodau[3]);
		dau4.setText(sodau[4]);
		dau5.setText(sodau[5]);
		dau6.setText(sodau[6]);
		dau7.setText(sodau[7]);
		dau8.setText(sodau[8]);
		dau9.setText(sodau[9]);

		duoi0.setText(soduoi[0]);
		duoi1.setText(soduoi[1]);
		duoi2.setText(soduoi[2]);
		duoi3.setText(soduoi[3]);
		duoi4.setText(soduoi[4]);
		duoi5.setText(soduoi[5]);
		duoi6.setText(soduoi[6]);
		duoi7.setText(soduoi[7]);
		duoi8.setText(soduoi[8]);
		duoi9.setText(soduoi[9]);

	}

}