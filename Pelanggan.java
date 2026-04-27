import java.util.Scanner;

class PelangganNode {
    String nomorAntrian, namaPelanggan;
    int totalBelanja;
    PelangganNode next;

    PelangganNode(String nomor, String nama, int total) {
        this.nomorAntrian = nomor;
        this.namaPelanggan = nama;
        this.totalBelanja = total;
        this.next = null;
    }
}

public class Main {
    static PelangganNode front = null, rear = null;
    static PelangganNode top = null;

    static void enqueue(String no, String nama, int total) {
        PelangganNode newNode = new PelangganNode(no, nama, total);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    static void layaniPelanggan() {
        if (front == null) {
            System.out.println("Antrian kosong!");
            return;
        }
        PelangganNode temp = front;
        front = front.next;
        if (front == null) rear = null;

        temp.next = top;
        top = temp;

        System.out.println("Melayani pelanggan " + temp.nomorAntrian + " (" + temp.namaPelanggan + ")");
        System.out.println("Transaksi disimpan ke riwayat.");
    }

    static void tampilkanAntrian() {
        if (front == null) {
            System.out.println("Tidak ada antrian saat ini.");
            return;
        }
        PelangganNode curr = front;
        System.out.println("\nANTRIAN SAAT INI:");
        while (curr != null) {
            System.out.println("No: " + curr.nomorAntrian + " | Nama: " + curr.namaPelanggan);
            curr = curr.next;
        }
    }

    static void lihatRiwayat() {
        if (top == null) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        PelangganNode curr = top;
        System.out.println("\nRIWAYAT TRANSAKSI (Terbaru di atas):");
        while (curr != null) {
            System.out.println("No: " + curr.nomorAntrian + " | Nama: " + curr.namaPelanggan + " | Total: " + curr.totalBelanja);
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu = 0;

        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian\n2. Layani Pelanggan\n3. Tampilkan Antrian\n4. Lihat Riwayat Transaksi\n5. Keluar");
            System.out.print("Pilih menu: ");
            
            if (sc.hasNextInt()) {
                menu = sc.nextInt();
                sc.nextLine(); 

                switch (menu) {
                    case 1:
                        System.out.print("Nomor Antrian: "); String no = sc.nextLine();
                        System.out.print("Nama Pelanggan: "); String na = sc.nextLine();
                        System.out.print("Total Belanja: "); int tot = sc.nextInt();
                        enqueue(no, na, tot);
                        break;
                    case 2: layaniPelanggan(); break;
                    case 3: tampilkanAntrian(); break;
                    case 4: lihatRiwayat(); break;
                    case 5: System.out.println("Keluar..."); break;
                    default: System.out.println("Menu tidak tersedia.");
                }
            } else {
                System.out.println("Input harus angka!");
                sc.next(); 
            }
        } while (menu != 5);
        sc.close();
    }
}