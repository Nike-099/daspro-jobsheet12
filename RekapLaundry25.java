import java.util.Scanner;

public class RekapLaundry25 {

    static Scanner input = new Scanner(System.in);

    static String[] namaLayanan;
    static int[] harga;
    static int[][] data;
    static int jumlahLayanan, jumlahHari;

    // Fungsi input data
    static void inputData() {
        System.out.print("Masukkan jumlah layanan: ");
        jumlahLayanan = input.nextInt();

        System.out.print("Masukkan jumlah hari: ");
        jumlahHari = input.nextInt();

        namaLayanan = new String[jumlahLayanan];
        harga = new int[jumlahLayanan];
        data = new int[jumlahLayanan][jumlahHari];

        for (int i = 0; i < jumlahLayanan; i++) {
            input.nextLine();

            System.out.print("\nMasukkan nama layanan ke-" + (i + 1) + ": ");
            namaLayanan[i] = input.nextLine();

            System.out.print("Masukkan harga per kg: Rp");
            harga[i] = input.nextInt();

            for (int j = 0; j < jumlahHari; j++) {
                System.out.print("Berat cucian Hari ke-" + (j + 1) + ": ");
                data[i][j] = input.nextInt();
            }
        }
    }

    // Fungsi menampilkan data
    static void tampilkanData() {
        System.out.println("\n========== REKAP DATA LAUNDRY ==========");
        System.out.printf("%-18s %-12s", "Layanan", "Harga/kg");

        for (int j = 0; j < jumlahHari; j++) {
            System.out.printf("%-12s", "Hari ke-" + (j + 1));
        }
        System.out.println();

        for (int i = 0; i < jumlahLayanan; i++) {
            System.out.printf("%-18s Rp%-10d", namaLayanan[i], harga[i]);

            for (int j = 0; j < jumlahHari; j++) {
                System.out.printf("%-12d", data[i][j]);
            }
            System.out.println();
        }
    }

    // Fungsi menghitung total berat per layanan
    static int totalPerLayanan(int[][] data, int idxLayanan) {
        int total = 0;

        for (int j = 0; j < jumlahHari; j++) {
            total += data[idxLayanan][j];
        }

        return total;
    }

    // Fungsi layanan dengan berat tertinggi
    static void layananTertinggi() {
        int max = 0;
        int indeks = 0;

        for (int i = 0; i < jumlahLayanan; i++) {
            int total = totalPerLayanan(data, i);

            if (total > max) {
                max = total;
                indeks = i;
            }
        }

        System.out.println("\nLayanan dengan berat tertinggi:");
        System.out.println(namaLayanan[indeks]);
        System.out.println("Total: " + max + " kg");
    }

    // Fungsi mencari hari tersibuk
    static int hariTersibuk(int[][] data) {
        int max = 0;
        int indeksHari = 0;

        for (int j = 0; j < jumlahHari; j++) {
            int total = 0;

            for (int i = 0; i < jumlahLayanan; i++) {
                total += data[i][j];
            }

            if (total > max) {
                max = total;
                indeksHari = j;
            }
        }

        return indeksHari;
    }

    // Fungsi menghitung pendapatan
    static void hitungPendapatan() {
        int totalPendapatan = 0;

        System.out.println("\n========== PENDAPATAN LAUNDRY ==========");

        for (int i = 0; i < jumlahLayanan; i++) {
            int pendapatan = totalPerLayanan(data, i) * harga[i];

            System.out.println(namaLayanan[i] + " : Rp" + pendapatan);

            totalPendapatan += pendapatan;
        }

        System.out.println("----------------------------------------");
        System.out.println("Total pendapatan: Rp" + totalPendapatan);
    }

    public static void main(String[] args) {
        inputData();
        tampilkanData();
        layananTertinggi();

        int indeks = hariTersibuk(data);

        System.out.println("\nHari paling sibuk: Hari ke-" + (indeks + 1));

        hitungPendapatan();
    }
}
