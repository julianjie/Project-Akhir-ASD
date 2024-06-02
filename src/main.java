import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Pasien> walkin = new LinkedList<Pasien>();
		Queue<Pasien> booking = new LinkedList<Pasien>();
		Queue<Pasien> qAkhir = new LinkedList<Pasien>();

		System.out.println("==============================================");
		System.out.println("===========APLIKASI ANTRI PASIEN==============");
		System.out.println("==============================================");
		System.out.print("\tMASUKAN JUMLAH PASIEN = ");
		int jumlah = Integer.parseInt(br.readLine());
		Pasien p[] = new Pasien[jumlah];	
		System.out.println("==============================================");
		
		for (int j = 0; j < p.length; j++) {
			p[j] = new Pasien();
			System.out.println("\tMASUKAN DATA PASIEN KE-"+(j+1));
			System.out.print("Masukan No-Pasien\t= ");
			p[j].setNoPasien(br.readLine());
			System.out.print("Masukan Nama\t\t= ");
			p[j].setNama(br.readLine());
			System.out.print("Masukan Umur\t\t= ");
			p[j].setUmur(Integer.parseInt(br.readLine()));
			System.out.print("Booking (Y/N)\t\t= ");
			p[j].setBooking(br.readLine());
			if(p[j].getBooking().equalsIgnoreCase("y"))
				booking.add(p[j]);
			else
				walkin.add(p[j]);
			System.out.println("----------------------------------------------");
		}
		
		System.out.println("==============================================");
		System.out.println("\t\tLIST WALK-IN");
		System.out.println("==============================================");
		System.out.println("No Antrian\tNo PASIEN\tNAMA\tUMUR");
		tampilQueue(walkin);
		System.out.println("==============================================");

		System.out.println("\t\tLIST BOOKING");
		System.out.println("==============================================");
		System.out.println("No Antrian\tNo PASIEN\tNAMA\tUMUR");
		tampilQueue(booking);
		System.out.println("==============================================");

		System.out.println("\t\tQUEUE AKHIR");
		System.out.println("==============================================");
		System.out.println("No Antrian\tNo PASIEN\tNAMA\tUMUR");
		//PENGABUNGAN QUEUE
		gabungQueue(walkin,booking,qAkhir);
		tampilQueue(qAkhir);
		System.out.println("==============================================");

	}
	public static void tampilQueue(Queue<Pasien> queue) {
		int i = 0;
		Iterator<Pasien> it = queue.iterator();
		while (it.hasNext()) {
			Pasien data = it.next();
			System.out.println((i += 1) + " " + data.tampil());
		}
	}
 	public static void gabungQueue(Queue<Pasien> walkin, Queue<Pasien> booking, Queue<Pasien> qAkhir) {
		Iterator<Pasien> it = booking.iterator();
		while (it.hasNext()) {
			Pasien data = it.next();
			qAkhir.add(data);
		}
		it = walkin.iterator();
		while (it.hasNext()) {
			Pasien data = it.next();
			qAkhir.add(data);
		}
	}

}
