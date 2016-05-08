package com.cudo.ketqua.xoso.soicau.giaimong;

import java.util.List;

import com.cudo.ketqua.xoso.soicau.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class GiaiMongMyAdapter extends ArrayAdapter<GiaiMongModel> {

	private final List<GiaiMongModel> list;
	private final Activity context;
	boolean checkAll_flag = false;
	boolean checkItem_flag = false;

	public GiaiMongMyAdapter(Activity context, List<GiaiMongModel> list) {
		super(context, R.layout.giai_mong_row, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView tvChu;
		protected TextView tvSo;
		protected CheckBox cbChon;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			convertView = inflator.inflate(R.layout.giai_mong_row, null);
			viewHolder = new ViewHolder();
			viewHolder.tvChu = (TextView) convertView.findViewById(R.id.tvChu);
			viewHolder.tvSo = (TextView) convertView.findViewById(R.id.tvSo);
			viewHolder.cbChon = (CheckBox) convertView
					.findViewById(R.id.cbChon);
			viewHolder.cbChon
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							int getPosition = (Integer) buttonView.getTag();
							list.get(getPosition).setSelected(
									buttonView.isChecked());
						}
					});
			convertView.setTag(viewHolder);
			convertView.setTag(R.id.tvChu, viewHolder.tvChu);
			convertView.setTag(R.id.tvSo, viewHolder.tvSo);
			convertView.setTag(R.id.cbChon, viewHolder.cbChon);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.cbChon.setTag(position); // This line is important.

		viewHolder.tvChu.setText(list.get(position).getChu());
		viewHolder.tvSo.setText(list.get(position).getSo());
		viewHolder.cbChon.setChecked(list.get(position).isSelected());

		return convertView;
	}
}
