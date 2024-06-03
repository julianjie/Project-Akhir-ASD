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

        while (true) {
            System.out.println("================================================");
            System.out.println("=============APLIKASI ANTRI PASIEN==============");
            System.out.println("================================================");
            System.out.println("1. Tambah Data Pasien");
            System.out.println("2. Lihat Antrian Pasien");
            System.out.println("3. Cari Data Pasien");
            System.out.println("4. Hapus Data Pasien");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = Integer.parseInt(br.readLine());
            System.out.println();
            switch (pilihan) {
                case 1:
                    tambahPasien(br, walkin, booking);
                    break;
                case 2:
                    lihatAntrian(walkin, booking, qAkhir);
                    break;
                case 3:
                    cariPasien(qAkhir, br);
                    break;
                case 4:
                    hapusPasien(walkin, booking, qAkhir, br);
                    break;
                case 5:
                    System.out.println("~~Terima Kasih Telah Menggunakan Applikasi Ini~~");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void tambahPasien(BufferedReader br, Queue<Pasien> walkin, Queue<Pasien> booking) throws IOException {
        System.out.print("Masukan jumlah pasien: ");
        int jumlah = Integer.parseInt(br.readLine());
        Pasien p[] = new Pasien[jumlah];
        System.out.println("================================================");

        for (int j = 0; j < p.length; j++) {
            p[j] = new Pasien();
            System.out.println("\tMASUKAN DATA PASIEN KE-" + (j + 1));
            System.out.print("Masukan No-Pasien\t= ");
            p[j].setNoPasien(br.readLine());
            System.out.print("Masukan Nama\t\t= ");
            p[j].setNama(br.readLine());
            System.out.print("Masukan Umur\t\t= ");
            p[j].setUmur(Integer.parseInt(br.readLine()));
            System.out.print("Booking (Y/N)\t\t= ");
            p[j].setBooking(br.readLine());
            if (p[j].getBooking().equalsIgnoreCase("y"))
                booking.add(p[j]);
            else
                walkin.add(p[j]);
            if (j< p.length-1)
            System.out.println("------------------------------------------------");
        }
    }

    public static void lihatAntrian(Queue<Pasien> walkin, Queue<Pasien> booking, Queue<Pasien> qAkhir) {
        System.out.println("================================================");
        System.out.println("\t\tLIST WALK-IN");
        System.out.println("================================================");
        System.out.printf("%-12s%-12s%-20s%-4s\n", "No Antrian", "No PASIEN", "NAMA", "UMUR");
        tampilQueue(walkin);
        System.out.println("================================================");

        System.out.println("\t\tLIST BOOKING");
        System.out.println("================================================");
        System.out.printf("%-12s%-12s%-20s%-4s\n", "No Antrian", "No PASIEN", "NAMA", "UMUR");
        tampilQueue(booking);
        System.out.println("================================================");

        System.out.println("\t\tQUEUE AKHIR");
        System.out.println("================================================");
        System.out.printf("%-12s%-12s%-20s%-4s\n", "No Antrian", "No PASIEN", "NAMA", "UMUR");
        gabungQueue(walkin, booking, qAkhir);
        tampilQueue(qAkhir);
        System.out.println("================================================");
    }

    public static void tampilQueue(Queue<Pasien> queue) {
        int i = 0;
        Iterator<Pasien> it = queue.iterator();
        while (it.hasNext()) {
            Pasien data = it.next();
            System.out.printf("%-12d%-12s%-20s%-4d\n", ++i, data.getNoPasien(), data.getNama(), data.getUmur());
        }
    }

    public static void gabungQueue(Queue<Pasien> walkin, Queue<Pasien> booking, Queue<Pasien> qAkhir) {
        qAkhir.clear(); 
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

    public static void cariPasien(Queue<Pasien> queue, BufferedReader br) throws IOException {
        System.out.print("Masukkan nomor pasien yang ingin dicari: ");
        String noPasienCari = br.readLine();
        Pasien pasienDitemukan = cariPasien(queue, noPasienCari);
        if (pasienDitemukan != null) {
            System.out.println("Data pasien ditemukan:");
            System.out.printf("%-12s%-12s%-20s%-4s\n", "No PASIEN", "NAMA", "UMUR", "BOOKING");
            System.out.printf("%-12s%-12s%-20d%-4s\n", pasienDitemukan.getNoPasien(), pasienDitemukan.getNama(), pasienDitemukan.getUmur(), pasienDitemukan.getBooking());
        } else {
            System.out.println("Data pasien tidak ditemukan.");
        }
    }

    public static Pasien cariPasien(Queue<Pasien> queue, String noPasien) {
        Iterator<Pasien> it = queue.iterator();
        while (it.hasNext()) {
            Pasien data = it.next();
            if (data.getNoPasien().equalsIgnoreCase(noPasien)) {
                return data;
            }
        }
        return null;
    }

    public static void hapusPasien(Queue<Pasien> walkin, Queue<Pasien> booking, Queue<Pasien> qAkhir, BufferedReader br) throws IOException {
        System.out.print("Masukkan nomor pasien yang ingin dihapus: ");
        String noPasienHapus = br.readLine();

        if (hapusDariAntrian(walkin, noPasienHapus) || hapusDariAntrian(booking, noPasienHapus)) {
            System.out.println("Data pasien dengan No Pasien " + noPasienHapus + " berhasil dihapus.");
        } else {
            System.out.println("Data pasien tidak ditemukan.");
        }
    }

    public static boolean hapusDariAntrian(Queue<Pasien> queue, String noPasien) {
        Iterator<Pasien> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Pasien pasien = iterator.next();
            if (pasien.getNoPasien().equalsIgnoreCase(noPasien)) {
                iterator.remove(); 
                return true;
            }
        }
        return false;
    }
}