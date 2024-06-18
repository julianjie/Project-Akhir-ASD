import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
	
    private static final String[] USERNAMES = {"reizan", "bonievitto", "julian", "genta"};
    
    private static final String PASSWORD_HASH = hashPassword("123");
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Pasien> walkin = new LinkedList<>();
        Queue<Pasien> booking = new LinkedList<>();
        Queue<Pasien> qAkhir = new LinkedList<>();
      
        
        if (login(br)) {
            while (true) {
                System.out.println("================================================");
                System.out.println("=============APLIKASI ANTRI PASIEN==============");
                System.out.println("================================================");
                System.out.println("1. Tambah Data Pasien");
                System.out.println("2. Lihat Antrian Pasien");
                System.out.println("3. Cari Data Pasien");
                System.out.println("4. Hapus Data Pasien");
                System.out.println("5. Update Data Pasien");
                System.out.println("6. Statistik Antrian");
                System.out.println("7. Keluar");
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
                        updatePasien(qAkhir, br);
                        break;
                    case 6:
                        statistikAntrian(walkin, booking);
                        break;
                    case 7:
                        System.out.println("~~Terima Kasih Telah Menggunakan Aplikasi Ini~~");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } else {
            System.out.println("Login gagal! Aplikasi akan keluar.");
            System.exit(0);
        }
    }

    private static boolean login(BufferedReader br) throws IOException, NoSuchAlgorithmException {
        System.out.println("================================================");
        System.out.println("=============LOGIN APLIKASI ANTRI=============== ");
        System.out.println("================================================");
        System.out.print("Username: ");
        String username = br.readLine();
        System.out.print("Password: ");
        String password = br.readLine();

        for (String user : USERNAMES) {
            if (user.equalsIgnoreCase(username) && PASSWORD_HASH.equals(hashPassword(password))) {
                return true;
            }
        }
        return false;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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
            while (true) {
                try {
                    p[j].setUmur(Integer.parseInt(br.readLine()));
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Umur tidak valid, masukkan angka: ");
                }
            }
            System.out.print("Booking (Y/N)\t\t= ");
            p[j].setBooking(br.readLine());
            if (p[j].getBooking().equalsIgnoreCase("y"))
                booking.add(p[j]);
            else
                walkin.add(p[j]);
            if (j < p.length - 1)
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
        String noPasien = br.readLine();
        boolean dihapus = hapusDariQueue(walkin, noPasien);
        if (!dihapus) {
            dihapus = hapusDariQueue(booking, noPasien);
        }
        if (!dihapus) {
            dihapus = hapusDariQueue(qAkhir, noPasien);
        }

        if (dihapus) {
            System.out.println("Data pasien berhasil dihapus.");
        } else {
            System.out.println("Data pasien tidak ditemukan.");
        }
    }

    public static boolean hapusDariQueue(Queue<Pasien> queue, String noPasien) {
        Iterator<Pasien> it = queue.iterator();
        while (it.hasNext()) {
            Pasien data = it.next();
            if (data.getNoPasien().equalsIgnoreCase(noPasien)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static void updatePasien(Queue<Pasien> queue, BufferedReader br) throws IOException {
        System.out.print("Masukkan nomor pasien yang ingin diupdate: ");
        String noPasien = br.readLine();
        Pasien pasienDitemukan = cariPasien(queue, noPasien);

        if (pasienDitemukan != null) {
            System.out.println("Data pasien ditemukan:");
            System.out.printf("%-12s%-12s%-20s%-4d\n", "No PASIEN", "NAMA", "UMUR", pasienDitemukan.getUmur());

            System.out.print("Masukkan nama baru (kosongkan jika tidak diubah): ");
            String namaBaru = br.readLine();
            if (!namaBaru.isEmpty()) {
                pasienDitemukan.setNama(namaBaru);
            }

            System.out.print("Masukkan umur baru (kosongkan jika tidak diubah): ");
            String umurBaru = br.readLine();
            if (!umurBaru.isEmpty()) {
                pasienDitemukan.setUmur(Integer.parseInt(umurBaru));
            }

            System.out.println("Data pasien berhasil diupdate.");
        } else {
            System.out.println("Data pasien tidak ditemukan.");
        }
    }

    public static void statistikAntrian(Queue<Pasien> walkin, Queue<Pasien> booking) {
        System.out.println("================================================");
        System.out.println("\t\tSTATISTIK ANTRIAN");
        System.out.println("================================================");
        System.out.println("Jumlah pasien walk-in: " + walkin.size());
        System.out.println("Jumlah pasien booking: " + booking.size());
        System.out.println("Total pasien: " + (walkin.size() + booking.size()));
        System.out.println("================================================");

        if (!walkin.isEmpty() || !booking.isEmpty()) {
            System.out.println("Rincian Umur Pasien Walk-In:");
            statistikUmur(walkin);
            System.out.println("Rincian Umur Pasien Booking:");
            statistikUmur(booking);
        }
    }

    public static void statistikUmur(Queue<Pasien> queue) {
        int[] umurKategori = new int[5];
        for (Pasien pasien : queue) {
            int umur = pasien.getUmur();
            if (umur < 5) {
                umurKategori[0]++;
            } else if (umur <= 13) {
                umurKategori[1]++;
            } else if (umur <= 18) {
                umurKategori[2]++;
            } else if (umur <= 60) {
                umurKategori[3]++;
            } else {
                umurKategori[4]++;
            }
        }
        System.out.println("Balita\t\t: " + umurKategori[0]);
        System.out.println("Anak-Anak\t: " + umurKategori[1]);
        System.out.println("Remaja\t\t: " + umurKategori[2]);
        System.out.println("Dewasa\t\t: " + umurKategori[3]);
        System.out.println("Lansia\t\t: " + umurKategori[4]);
    }
}