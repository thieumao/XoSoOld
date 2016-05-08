package com.cudo.ketqua.xoso.soicau.dudoan;

import java.util.ArrayList;

import com.cudo.ketqua.xoso.soicau.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DuDoanMyAdapter extends ArrayAdapter<DuDoanItemContent> {
	Context mContext;
	ArrayList<DuDoanItemContent> items;

	public DuDoanMyAdapter(Context context, int textViewResourceId,
			ArrayList<DuDoanItemContent> objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
		items = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = LayoutInflater.from(mContext).inflate(
					R.layout.du_doan_row_item, null);
		}
		DuDoanItemContent iContent = items.get(position);
		if (iContent != null) {
			TextView tvTen = (TextView) v.findViewById(R.id.tvDuDoanTen);
			TextView tvKetQua = (TextView) v.findViewById(R.id.tvDuDoanKetQua);
			TextView tvNoiDung = (TextView) v
					.findViewById(R.id.tvDuDoanNoiDung);
			TextView tvThoiGian = (TextView) v
					.findViewById(R.id.tvDuDoanThoiGian);

			if (tvTen != null) {
				tvTen.setText(iContent.getTen());
			}
			if (tvKetQua != null) {
				tvKetQua.setText(iContent.getKetqua());
			}
			if (tvNoiDung != null) {
				tvNoiDung.setText(iContent.getNoidung());
			}
			if (tvThoiGian != null) {
				tvThoiGian.setText(iContent.getThoigian());
			}

		}
		return v;
	}
}