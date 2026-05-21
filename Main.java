// ============================================================
//  MAIN: Main.java
//  Titik masuk program. Menjalankan simulasi lengkap:
//    1. Laptop masuk ke antrean (Queue)
//    2. Pindah ke array, lalu diurutkan (Selection & Bubble Sort)
//    3. Dimasukkan ke BST
//    4. Pencarian tiket di BST
// ============================================================

public class Main {

    // Separator dekoratif untuk memperindah output
    private static final String GARIS  = "=".repeat(55);
    private static final String GARIS2 = "-".repeat(55);

    public static void main(String[] args) {

        // ── LANGKAH 1: Buat antrean dan masukkan laptop ─────────
        cetakHeader("LANGKAH 1: Laptop Masuk ke Antrean (Queue)");
        System.out.println("  Laptop didaftarkan sesuai urutan kedatangan.\n");

        LaptopQueue antrean = new LaptopQueue();

        antrean.enqueue(new Laptop(101, 3, 2.0));
        antrean.enqueue(new Laptop(205, 1, 4.5));
        antrean.enqueue(new Laptop(103, 5, 1.0));
        antrean.enqueue(new Laptop(204, 2, 3.0));
        antrean.enqueue(new Laptop(102, 4, 0.5));
        antrean.enqueue(new Laptop(305, 1, 2.5));

        System.out.println("\n  Total laptop dalam antrean: " + antrean.size());


        // ── LANGKAH 2A: Pindah ke array, urutkan by urgensi ─────
        cetakHeader("LANGKAH 2A: Urutkan berdasarkan Urgensi (Selection Sort)");
        System.out.println("  Algoritma : Selection Sort");
        System.out.println("  Kunci     : tingkatUrgensi (1 = paling mendesak)\n");

        // Salin array dari queue pertama untuk UrgencySorter
        Laptop[] arrayUrgensi = antrean.pindahKeArray();

        ServiceSorter urgencySorter = new UrgencySorter();
        urgencySorter.sort(arrayUrgensi);

        System.out.println("  Hasil setelah Selection Sort:");
        urgencySorter.tampilkanHasil(arrayUrgensi);


        // ── LANGKAH 2B: Isi ulang queue, urutkan by lama servis ─
        // (Queue sudah kosong setelah pindahKeArray(), isi ulang dulu)
        cetakHeader("LANGKAH 2B: Urutkan berdasarkan Lama Servis (Bubble Sort)");
        System.out.println("  Algoritma : Bubble Sort");
        System.out.println("  Kunci     : lamaServis (tercepat duluan)\n");

        // Isi ulang antrean dari array urgensi (urutan tidak soal di sini)
        LaptopQueue antrean2 = new LaptopQueue();
        for (Laptop l : arrayUrgensi) antrean2.enqueue(l);
        System.out.println();

        Laptop[] arrayWaktu = antrean2.pindahKeArray();

        ServiceSorter timeSorter = new TimeSorter();
        timeSorter.sort(arrayWaktu);

        System.out.println("  Hasil setelah Bubble Sort:");
        timeSorter.tampilkanHasil(arrayWaktu);


        // ── LANGKAH 3: Masukkan ke BST (gunakan hasil UrgencySorter)
        cetakHeader("LANGKAH 3: Masukkan Data ke Binary Search Tree (BST)");
        System.out.println("  Key BST : idTiket");
        System.out.println("  Sumber  : array hasil Selection Sort\n");

        LaptopBST bst = new LaptopBST();
        for (Laptop laptop : arrayUrgensi) {
            bst.insert(laptop);
            System.out.println("  [Insert BST] Tiket #" + laptop.getIdTiket());
        }

        System.out.println("\n  Tampilan In-Order BST (urut berdasarkan idTiket):");
        bst.tampilkanInOrder();


        // ── LANGKAH 4: Pencarian tiket di BST ───────────────────
        cetakHeader("LANGKAH 4: Pencarian Tiket di BST");
        System.out.println("  Mencari tiket nomor 105 dan 204...\n");

        cariTiket(bst, 105);
        cariTiket(bst, 204);

        cetakHeader("SELESAI");
    }


    // -------------------------------------------------------
    //  HELPER: cari tiket dan tampilkan hasil dengan jelas
    // -------------------------------------------------------
    private static void cariTiket(LaptopBST bst, int idTiket) {
        Laptop hasil = bst.search(idTiket);

        if (hasil != null) {
            System.out.println("  ✔ Tiket #" + idTiket + " DITEMUKAN → " + hasil);
        } else {
            System.out.println("  ✘ Tiket #" + idTiket + " TIDAK ditemukan dalam sistem.");
        }
    }


    // -------------------------------------------------------
    //  HELPER: cetak header bagian dengan garis pembatas
    // -------------------------------------------------------
    private static void cetakHeader(String judul) {
        System.out.println("\n" + GARIS);
        System.out.println("  " + judul);
        System.out.println(GARIS);
    }
}
