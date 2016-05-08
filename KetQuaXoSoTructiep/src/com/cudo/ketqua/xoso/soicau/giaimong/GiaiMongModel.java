package com.cudo.ketqua.xoso.soicau.giaimong;

public class GiaiMongModel {
	private String chu;
	private String so;
	private boolean selected;

	public GiaiMongModel(String chu, String so) {
		this.chu = chu;
		this.so = so;
	}

	public String getChu() {
		return chu;
	}

	public void setChu(String chu) {
		this.chu = chu;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
