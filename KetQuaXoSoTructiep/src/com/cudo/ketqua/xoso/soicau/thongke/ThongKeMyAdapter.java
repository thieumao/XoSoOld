package com.cudo.ketqua.xoso.soicau.thongke;

import java.util.ArrayList;

import com.cudo.ketqua.xoso.soicau.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ThongKeMyAdapter extends ArrayAdapter<ThongKeItemContent> {
	Context mContext;
	ArrayList<ThongKeItemContent> items;

	public ThongKeMyAdapter(Context context, int textViewResourceId,
			ArrayList<ThongKeItemContent> objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
		items = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = LayoutInflater.from(mContext).inflate(
					R.layout.thong_ke_row_item, null);
		}
		ThongKeItemContent iContent = items.get(position);
		if (iContent != null) {
			TextView tvDayso = (TextView) v.findViewById(R.id.tvDayso);
			TextView tvDayXuatHien = (TextView) v
					.findViewById(R.id.tvDayXuatHien);
			TextView tvDayPhanTram = (TextView) v
					.findViewById(R.id.tvDayPhanTram);

			if (tvDayso != null) {
				tvDayso.setText(iContent.getSo());
			}
			if (tvDayXuatHien != null) {
				tvDayXuatHien.setText(iContent.getXuathien());
			}
			if (tvDayPhanTram != null) {
				tvDayPhanTram.setText(iContent.getPhantram());
			}
		}
		return v;
	}
}