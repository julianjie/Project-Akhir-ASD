public class Orang {
    private String nama;
    private int umur;

    public Orang(String nama,int umur) {
        this.nama = nama;
        this.umur = umur;
    }
    public Orang() {
        this.nama = null;
        this.umur = 0;
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
}