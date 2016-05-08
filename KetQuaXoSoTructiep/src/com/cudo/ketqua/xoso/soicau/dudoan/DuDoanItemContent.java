package com.cudo.ketqua.xoso.soicau.dudoan;

public class DuDoanItemContent {
	private String ten;
	private String ketqua;
	private String noidung;
	private String thoigian;

	public DuDoanItemContent(String ten, String ketqua, String noidung,
			String thoigian) {
		this.ten = ten;
		this.ketqua = ketqua;
		this.noidung = noidung;
		this.thoigian = thoigian;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getKetqua() {
		return ketqua;
	}

	public void setKetqua(String ketqua) {
		this.ketqua = ketqua;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public String getThoigian() {
		return thoigian;
	}

	public void setThoigian(String thoigian) {
		this.thoigian = thoigian;
	}

}
