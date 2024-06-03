class Pasien {
	private String noPasien;
	private String nama;
	private int umur;
	private String booking;

	public String getNoPasien() {
		return noPasien;
	}

	public void setNoPasien(String noPasien) {
		this.noPasien = noPasien;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getUmur() {
		return umur;
	}

	public void setUmur(int umur) {
		this.umur = umur;
	}

	public String getBooking() {
		return booking;
	}

	public void setBooking(String booking) {
		this.booking = booking;
	}

	public String tampil() {
		return String.format("%-12s%-20s%-4d", noPasien, nama, umur);
	}
}
