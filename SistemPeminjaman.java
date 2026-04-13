package CaseMethod1;
import java.util.Scanner;

public class SistemPeminjaman {
    public static void main(String[] args) {
        // Data Mahasiswa 
        Mahasiswa[] mhsArr = {
            new Mahasiswa("22001", "Andi", "Teknik Informatika"),
            new Mahasiswa("22002", "Budi", "Teknik Informatika"),
            new Mahasiswa("22003", "Citra", "Sistem Informasi Bisnis")
        };

        // Data Buku 
        Buku[] bukuArr = {
            new Buku("B001", "Algoritma", 2020),
            new Buku("B002", "Basis Data", 2019),
            new Buku("B003", "Pemrograman", 2021),
            new Buku("B004", "Fisika", 2024)
        };

        // Data Peminjaman (Relasi Array of Object) 
        Peminjaman[] pmjArr = {
            new Peminjaman(mhsArr[0], bukuArr[0], 7),
            new Peminjaman(mhsArr[1], bukuArr[1], 3),
            new Peminjaman(mhsArr[2], bukuArr[2], 10),
            new Peminjaman(mhsArr[2], bukuArr[3], 6),
            new Peminjaman(mhsArr[0], bukuArr[1], 4)
        };

        Scanner sc = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa\n2. Tampilkan Buku\n3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda\n5. Cari Berdasarkan NIM\n0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    System.out.println("Daftar Mahasiswa:");
                    for (Mahasiswa m : mhsArr) m.tampilMahasiswa();
                    break;
                case 2:
                    System.out.println("Daftar Buku:");
                    for (Buku b : bukuArr) b.tampilBuku();
                    break;
                case 3:
                    System.out.println("Data Peminjaman:");
                    for (Peminjaman p : pmjArr) p.tampilPeminjaman();
                    break;
                case 4:
                    // Insertion Sort - Denda Terbesar [cite: 156]
                    for (int i = 1; i < pmjArr.length; i++) {
                        Peminjaman key = pmjArr[i];
                        int j = i - 1;
                        while (j >= 0 && pmjArr[j].denda < key.denda) {
                            pmjArr[j + 1] = pmjArr[j];
                            j--;
                        }
                        pmjArr[j + 1] = key;
                    }
                    System.out.println("Setelah diurutkan (Denda terbesar):");
                    for (Peminjaman p : pmjArr) p.tampilPeminjaman();
                    break;
                case 5:
                    // Sequential Search [cite: 156]
                    System.out.print("Masukkan NIM: ");
                    String cari = sc.nextLine();
                    boolean ditemukan = false;
                    for (Peminjaman p : pmjArr) {
                        if (p.mhs.nim.equals(cari)) {
                            p.tampilPeminjaman();
                            ditemukan = true;
                        }
                    }
                    if (!ditemukan) System.out.println("Data tidak ditemukan.");
                    break;
            }
        } while (pilih != 0);
    }
}