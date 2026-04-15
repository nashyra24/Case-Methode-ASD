package CaseMethod1;
import java.util.Scanner;


public class SistemPeminjaman {
    public static void main(String[] args) {

        Mahasiswa[] mhsArr = {
            new Mahasiswa("22001", "Andi", "Teknik Informatika"),
            new Mahasiswa("22002", "Budi", "Teknik Informatika"),
            new Mahasiswa("22003", "Citra", "Sistem Informasi Bisnis")
        };

        Buku[] bukuArr = {
            new Buku("B001", "Algoritma", 2020),
            new Buku("B002", "Basis Data", 2019),
            new Buku("B003", "Pemrograman", 2021),
            new Buku("B004", "Fisika", 2024)
        };

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
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda");
            System.out.println("5. Cari Berdasarkan NIM");
            System.out.println("6. Tambah Data Peminjaman");
            System.out.println("7. Tampilkan Statistik");
            System.out.println("8. Laporan Per Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    for (Mahasiswa m : mhsArr) m.tampilMahasiswa();
                    break;

                case 2:
                    for (Buku b : bukuArr) b.tampilBuku();
                    break;

                case 3:
                    for (Peminjaman p : pmjArr) p.tampilPeminjaman();
                    break;

                case 4:
                    System.out.println("Setelah diurutkan dengan Insertion Sort (Denda terbesar):");
                    for (int i = 1; i < pmjArr.length; i++) {
                        Peminjaman key = pmjArr[i];
                        int j = i - 1;
                        while (j >= 0 && pmjArr[j].denda < key.denda) {
                            pmjArr[j + 1] = pmjArr[j];
                            j--;
                        }
                        pmjArr[j + 1] = key;
                    }
                    for (Peminjaman p : pmjArr) {
                         p.tampilPeminjaman();
                    }
                break;


                case 5:
                    System.out.print("Masukkan NIM: ");
                    String cari = sc.nextLine();
                    // ARRAY
                    Peminjaman[] tempArr = new Peminjaman[pmjArr.length];
                    for (int i = 0; i < pmjArr.length; i++) {
                        tempArr[i] = pmjArr[i];
                    }
                    //SORT ASCENDING NIM
                    for (int i = 1; i < tempArr.length; i++) {
                        Peminjaman key = tempArr[i];
                        int j = i - 1;

                        while (j >= 0 && tempArr[j].mhs.nim.compareTo(key.mhs.nim) > 0) {
                             tempArr[j + 1] = tempArr[j];
                            j--;
                         }
                        tempArr[j + 1] = key;
                }
                //BINARY SEACRH
                int left = 0, right = tempArr.length - 1;
                 int foundIndex = -1;

                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (tempArr[mid].mhs.nim.equals(cari)) {
                        foundIndex = mid;
                        break;
                    } else if (tempArr[mid].mhs.nim.compareTo(cari) < 0) {
                         left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (foundIndex == -1) {
                    System.out.println("Data tidak ditemukan!");
                } else {
                    System.out.println("[Binary Search] Data ditemukan:");

                // EXPANSI KE KIRI & KANAN
                int i = foundIndex;

                // ke kiri
                while (i >= 0 && tempArr[i].mhs.nim.equals(cari)) {
                    i--;
                }
                i++;

                // ke kanan
                while (i < tempArr.length && tempArr[i].mhs.nim.equals(cari)) {
                    tempArr[i].tampilPeminjaman();
                    i++;
                }
            }
            break;
                case 6:
                    System.out.print("Masukkan NIM: ");
                    String nim = sc.nextLine();

                    Mahasiswa mhs = null;
                    for (Mahasiswa m : mhsArr) {
                        if (m.nim.equals(nim)) {
                            mhs = m;
                            break;
                        }
                    }

                    if (mhs == null) {
                        System.out.println("NIM tidak ditemukan!");
                        break;
                    }

                    System.out.print("Masukkan Kode Buku: ");
                    String kode = sc.nextLine();

                    Buku buku = null;
                    for (Buku b : bukuArr) {
                        if (b.kodeBuku.equals(kode)) {
                            buku = b;
                            break;
                        }
                    }

                    if (buku == null) {
                        System.out.println("Kode buku tidak ditemukan!");
                        break;
                    }

                    System.out.print("Masukkan Lama Pinjam: ");
                    int lama = sc.nextInt();

                    Peminjaman baru = new Peminjaman(mhs, buku, lama);

                    // ARRAY
                    Peminjaman[] temp = new Peminjaman[pmjArr.length + 1];
                    for (int i = 0; i < pmjArr.length; i++) {
                        temp[i] = pmjArr[i];
                    }
                    temp[pmjArr.length] = baru;
                    pmjArr = temp;

                    System.out.println("Data peminjaman berhasil ditambahkan!");
                    break;

                case 7:
                    int totalDenda = 0;
                    int terlambat = 0;
                    int tepatWaktu = 0;

                    for (Peminjaman p : pmjArr) {
                        totalDenda += p.denda;
                        if (p.terlambat > 0) {
                            terlambat++;
                        } else {
                            tepatWaktu++;
                        }
                    }

                    System.out.println("\n=== STATISTIK PEMINJAMAN ===");
                    System.out.println("Total Denda Keseluruhan: Rp " + totalDenda);
                    System.out.println("Jumlah Peminjaman Terlambat: " + terlambat);
                    System.out.println("Jumlah Peminjaman Tepat Waktu: " + tepatWaktu);
                    break;
                case 8:
                    System.out.println("\n=== LAPORAN PER MAHASISWA ===");

                    // ARRAY LAPORAN
                        LaporanMahasiswa[] laporanArr = new LaporanMahasiswa[mhsArr.length];

                         for (int i = 0; i < mhsArr.length; i++) {
        laporanArr[i] = new LaporanMahasiswa(mhsArr[i]);
        laporanArr[i].hitungLaporan(pmjArr);
    }

    // TAMPILKAN SEMUA
    for (LaporanMahasiswa l : laporanArr) {
        l.tampilLaporan();
    }
    break;
            }

        } while (pilih != 0);
    }
}