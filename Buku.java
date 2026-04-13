package CaseMethod1;

public class Buku {
    String kodeBuku, judul;
    int tahunTerbit;

    public Buku(String kodeBuku, String judul, int tahun) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.tahunTerbit = tahun;
    }
    public void tampilBuku() {
        System.out.println("Kode Buku: "+kodeBuku+" | Judul: "+judul+" | Tahun: "+tahunTerbit);
    }
}
