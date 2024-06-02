
public class Pasien extends Orang{
	private String noPasien,booking;
	
	public Pasien(String noPasien,String nama,int umur,String booking) {
		super(nama, umur);
		this.noPasien = noPasien;
		this.booking = booking;
	}
	public String getBooking() {
		return booking;
	}
	public void setBooking(String booking) {
		this.booking = booking;
	}
	public Pasien() {
		super();
		this.noPasien = null;
	}
	
	public String getNoPasien() {
		return noPasien;
	}

	public void setNoPasien(String noPasien) {
		this.noPasien = noPasien;
	}
	public String tampil() {
		return "\t\t"+getNoPasien()+"\t\t"+ getNama() +"\t"+ getUmur();
	}
}
