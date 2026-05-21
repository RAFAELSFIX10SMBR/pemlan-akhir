// ============================================================
//  STRUKTUR DATA: LaptopQueue  (Queue — FIFO)
//
//  Menampung laptop sesuai urutan kedatangan.
//  Laptop yang pertama datang akan pertama diproses (FIFO).
// ============================================================

import java.util.LinkedList;
import java.util.Queue;

public class LaptopQueue {

    // LinkedList sudah mengimplementasikan interface Queue di Java
    private Queue<Laptop> antrean = new LinkedList<>();

    // --- Tambahkan laptop ke belakang antrean ---
    public void enqueue(Laptop laptop) {
        antrean.offer(laptop);
        System.out.println("  [Masuk Antrean] " + laptop);
    }

    // --- Ambil laptop dari depan antrean ---
    public Laptop dequeue() {
        return antrean.poll();
    }

    // --- Cek apakah antrean kosong ---
    public boolean isEmpty() {
        return antrean.isEmpty();
    }

    // --- Ukuran antrean saat ini ---
    public int size() {
        return antrean.size();
    }

    // -------------------------------------------------------
    //  Pindahkan SEMUA laptop dari Queue ke dalam Array.
    //  Array ini yang akan diurutkan oleh Sorter.
    // -------------------------------------------------------
    public Laptop[] pindahKeArray() {
        Laptop[] arr = new Laptop[antrean.size()];
        int i = 0;
        while (!antrean.isEmpty()) {
            arr[i++] = dequeue();
        }
        return arr;
    }
}
