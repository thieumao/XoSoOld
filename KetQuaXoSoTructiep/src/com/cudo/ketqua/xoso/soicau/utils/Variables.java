package com.cudo.ketqua.xoso.soicau.utils;

import com.cudo.ketqua.xoso.soicau.maocrypto;
import com.startapp.android.publish.StartAppAd;

public class Variables {

	// Kết Quả
	public static final String[] XoSoBaMien = { "Xổ Số Miền Bắc",
			"Xố Số Miền Trung", "Xổ Số Miền Nam" };
	public static final String[] TinhCaBaMien = { "Miền Bắc", "Đà Nẵng",
			"Quảng Nam", "Khánh Hòa", "Đắc Lắc", "Bình Định", "Đắc Nông",
			"Gia Lai", "Kon Tum", "Ninh Thuận", "Phú Yên", "Quảng Bình",
			"Quảng Ngãi", "Quảng Trị", "Thừa Thiên Huế", "Bình Dương",
			"Tp. Hồ Chí Minh", "Bình Thuận", "Cà Mau", "Đồng Tháp", "Bạc Liêu",
			"Bến Tre", "Đồng Nai", "Sóc Trăng", "Cần Thơ", "An Giang",
			"Vũng Tàu", "Bình Phước", "Hậu Giang", "Kiên Giang", "Lâm Đồng",
			"Tây Ninh", "Trà Vinh", "Vĩnh Long", "Tiền Giang", "Long An" };
	public static final String[] TinhCaBaMien_LINKS = { "xo-so-mien-bac",
			"xo-so-da-nang", "xo-so-quang-nam", "xo-so-khanh-hoa",
			"xo-so-dac-lac", "xo-so-binh-dinh", "xo-so-dac-nong",
			"xo-so-gia-lai", "xo-so-kon-tum", "xo-so-ninh-thuan",
			"xo-so-phu-yen", "xo-so-quang-binh", "xo-so-quang-ngai",
			"xo-so-quang-tri", "xo-so-thua-thien-hue", "xo-so-binh-duong",
			"xo-so-tp-ho-chi-minh", "xo-so-binh-thuan", "xo-so-ca-mau",
			"xo-so-dong-thap", "xo-so-bac-lieu", "xo-so-ben-tre",
			"xo-so-dong-nai", "xo-so-soc-trang", "xo-so-can-tho",
			"xo-so-an-giang", "xo-so-vung-tau", "xo-so-binh-phuoc",
			"xo-so-hau-giang", "xo-so-kien-giang", "xo-so-lam-dong",
			"xo-so-tay-ninh", "xo-so-tra-vinh", "xo-so-vinh-long",
			"xo-so-tien-giang", "xo-so-long-an" };

	// Miền Bắc
	public static final String[] TinhMienBac = { "Miền Bắc" };
	public static final String[] TinhMienBac_LINKS = { "xo-so-mien-bac" };

	// Miền Trung
	public static final String[] TinhMienTrung = { "Đà Nẵng", "Quảng Nam",
			"Khánh Hòa", "Đắc Lắc", "Bình Định", "Đắc Nông", "Gia Lai",
			"Kon Tum", "Ninh Thuận", "Phú Yên", "Quảng Bình", "Quảng Ngãi",
			"Quảng Trị", "Thừa Thiên Huế" };
	public static final String[] TinhMienTrung_LINKS = { "xo-so-da-nang",
			"xo-so-quang-nam", "xo-so-khanh-hoa", "xo-so-dac-lac",
			"xo-so-binh-dinh", "xo-so-dac-nong", "xo-so-gia-lai",
			"xo-so-kon-tum", "xo-so-ninh-thuan", "xo-so-phu-yen",
			"xo-so-quang-binh", "xo-so-quang-ngai", "xo-so-quang-tri",
			"xo-so-thua-thien-hue" };

	// Miền Nam
	public static final String[] TinhMienNam = { "Bình Dương",
			"Tp. Hồ Chí Minh", "Bình Thuận", "Cà Mau", "Đồng Tháp", "Bạc Liêu",
			"Bến Tre", "Đồng Nai", "Sóc Trăng", "Cần Thơ", "An Giang",
			"Vũng Tàu", "Bình Phước", "Hậu Giang", "Kiên Giang", "Lâm Đồng",
			"Tây Ninh", "Trà Vinh", "Vĩnh Long", "Tiền Giang", "Long An" };
	public static final String[] TinhMienMam_LINKS = { "xo-so-binh-duong",
			"xo-so-tp-ho-chi-minh", "xo-so-binh-thuan", "xo-so-ca-mau",
			"xo-so-dong-thap", "xo-so-bac-lieu", "xo-so-ben-tre",
			"xo-so-dong-nai", "xo-so-soc-trang", "xo-so-can-tho",
			"xo-so-an-giang", "xo-so-vung-tau", "xo-so-binh-phuoc",
			"xo-so-hau-giang", "xo-so-kien-giang", "xo-so-lam-dong",
			"xo-so-tay-ninh", "xo-so-tra-vinh", "xo-so-vinh-long",
			"xo-so-tien-giang", "xo-so-long-an" };

	// Hôm Nay
	public static final String[] HomNay = { "Kết Quả Hôm Nay",
			"Kết Quả Hôm Qua", "Kết Quả Hôm Khác" };
	public static final String[] HomKhac = { "Kết Quả Hôm Khác" };

	// Ngày mở thưởng
	public static final String[] NgayChuNhat = { "Miền Bắc", "Khánh Hòa",
			"Kon Tum", "Kiên Giang", "Lâm Đồng", "Tiền Giang" };
	public static final String[] NgayThu2 = { "Miền Bắc", "Phú Yên",
			"Thừa Thiên Huế", "Tp. Hồ Chí Minh", "Cà Mau", "Đồng Tháp" };
	public static final String[] NgayThu3 = { "Miền Bắc", "Quảng Nam",
			"Đắc Lắc", "Bạc Liêu", "Bến Tre", "Vũng Tàu" };
	public static final String[] NgayThu4 = { "Miền Bắc", "Đà Nẵng",
			"Đồng Nai", "Sóc Trăng", "Cần Thơ", "Khánh Hòa" };
	public static final String[] NgayThu5 = { "Miền Bắc", "Bình Định",
			"Quảng Bình", "Quảng Trị", "Bình Thuận", "An Giang", "Tây Ninh" };
	public static final String[] NgayThu6 = { "Miền Bắc", "Gia Lai",
			"Ninh Thuận", "Bình Dương", "Trà Vinh", "Vĩnh Long" };
	public static final String[] NgayThu7 = { "Miền Bắc", "Đắc Nông",
			"Quảng Ngãi", "Bình Phước", "Hậu Giang", "Long An",
			"Tp. Hồ Chí Minh", "Đà Nẵng" };

	// Danh sách giải
	public static final String[] DanhSachGiai = { "Đặc Biệt", "Giải Nhất",
			"Giải Nhì", "Giải Ba", "Giải Tư", "Giải Năm", "Giải Sáu",
			"Giải Bảy", "Giải Tám" };
	// Danh sách giải phân tích
	public static final String[] GiaiPhanTich = { "Đặc Biệt", "Nhất To",
			"Hai Cửa", "Lô" };
	public static final String[] GiaiPhanTich_LINKS = { "dacbiet", "nhatto",
			"haicua", "lo" };
	// Số 0-9
	public static final String[] So09 = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9" };
	// Số Fragment Trang Kết Quả
	public static final int soTrangKQ = 100;

	// Danh sach LINK

	// http://xoso.android.vn/appxoso/layngayganday.php?vung=xo-so-mien-bac&limit=10
	// public static final String linkSoiCau2 =
	// "http://xoso.android.vn/appxoso/layngayganday.php";
	// public static final String linkSoiCau_en =
	// "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL2xheW5nYXlnYW5kYXkucGhw";
	// public static final String linkSoiCau =
	// maocrypto.decodebase64(linkSoiCau_en);
	public static final String linkSoiCau_en = "WVVoU01HTkViM1pNTTJoMll6STRkVmxYTld0amJUbHdXa00xTW1KcE9XaGpTRUkwWWpOT2Rrd3llR2hsVnpWdVdWaHNibGxYTld0WldHdDFZMGRvZHc9PQ==";
	public static final String linkSoiCau = maocrypto
			.badecodebase64(linkSoiCau_en);

	// http://xoso.android.vn/appxoso/laymotngay.php?vung=xo-so-mien-bac&thoigian=05/01/2014
	// public static final String linkHomKhac2 =
	// "http://xoso.android.vn/appxoso/laymotngay.php";
	public static final String linkHomKhac_en = "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL2xheW1vdG5nYXkucGhw";
	public static final String linkHomKhac = maocrypto
			.decodebase64(linkHomKhac_en);

	// http://xoso.android.vn/appxoso/thongkedauduoi.php?duoidau=duoi&vung=xo-so-mien-bac&loai=lo&limit=100
	// public static final String linkDauDuoi2 =
	// "http://xoso.android.vn/appxoso/thongkedauduoi.php";
	public static final String linkDauDuoi_en = "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL3Rob25na2VkYXVkdW9pLnBocA==";
	public static final String linkDauDuoi = maocrypto
			.decodebase64(linkDauDuoi_en);

	// http://xoso.android.vn/appxoso/thongke0099.php?vung=xo-so-kon-tum&limit=70&loai=haicua
	// public static final String link00992 =
	// "http://xoso.android.vn/appxoso/thongke0099.php";
	public static final String link0099_en = "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL3Rob25na2UwMDk5LnBocA==";
	public static final String link0099 = maocrypto.decodebase64(link0099_en);

	// http://xoso.android.vn/appxoso/congdong.php
	// public static final String linkCongDong2 =
	// "http://xoso.android.vn/appxoso/congdong.php";
	public static final String linkCongDong_en = "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL2Nvbmdkb25nLnBocA==";
	public static final String linkCongDong = maocrypto
			.decodebase64(linkCongDong_en);

	// http://xoso.android.vn/appxoso/dudoan.php
	// ?limit=1&ten=1&ketqua=1&noidung=1
	// public static final String linkDuDoan2 =
	// "http://xoso.android.vn/appxoso/dudoan.php";
	public static final String linkDuDoan_en = "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL2R1ZG9hbi5waHA=";
	public static final String linkDuDoan = maocrypto
			.decodebase64(linkDuDoan_en);

	// http://xoso.android.vn/appxoso/thongkedudoan.php?limit=2
	// public static final String linkThongKeDuDoan2 =
	// "http://xoso.android.vn/appxoso/thongkedudoan.php";
	public static final String linkThongKeDuDoan_en = "aHR0cDovL3hvc28uYW5kcm9pZC52bi9hcHB4b3NvL3Rob25na2VkdWRvYW4ucGhw";
	public static final String linkThongKeDuDoan = maocrypto
			.decodebase64(linkThongKeDuDoan_en);

	public static final String linkUpdateVersion = "http://xoso.android.vn/appxoso/updateVersion.php";
	public static final String linkADS = "http://xoso.android.vn/appxoso/showAds.php";

	// DEV ID
	public static String DEV_ID = "CUDO";

	// Starpp App
	//StartAppAd.init(this, "104144450", "203881518");
	public static final String STARTAPP_DEV_ID = "104144450";
	public static final String STARTAPP_APP_ID = "203881518";
}
