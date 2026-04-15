package CaseMethod1;

public class LaporanMahasiswa {
    Mahasiswa mahasiswa;
    int totalPinjam;
    int totalDenda;
    int totalTerlambat;

    public LaporanMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
    // MENGHITUNG LAPORAN
    public void hitungLaporan(Peminjaman[] pinjam) {
        totalPinjam = 0;
        totalDenda = 0;
        totalTerlambat = 0;

        for (Peminjaman p : pinjam) {
            if (p.mhs.nim.equals(mahasiswa.nim)) {
                totalPinjam++;
                totalDenda += p.denda;

                if (p.terlambat > 0) {
                    totalTerlambat++;
                }
            }
        }
    }
    public void tampilLaporan() {
        System.out.println(
            "NIM: " + mahasiswa.nim +
            " | Nama: " + mahasiswa.nama +
            " | Total Pinjam: " + totalPinjam +
            " | Total Denda: Rp " + totalDenda +
            " | Terlambat: " + totalTerlambat + "x"
        );
    }
}