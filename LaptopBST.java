// ============================================================
//  STRUKTUR DATA: LaptopBST  (Binary Search Tree)
//
//  Menyimpan laptop berdasarkan idTiket sebagai key.
//  BST memudahkan pencarian tiket dengan kompleksitas O(log n)
//  rata-rata, jauh lebih cepat dari pencarian linear O(n).
//
//  Aturan BST:
//    - idTiket lebih kecil → simpan ke subtree KIRI
//    - idTiket lebih besar → simpan ke subtree KANAN
// ============================================================

public class LaptopBST {

    // -------------------------------------------------------
    //  NODE: unit terkecil dalam BST
    // -------------------------------------------------------
    private static class Node {
        Laptop data;
        Node   kiri;   // anak kiri  (idTiket lebih kecil)
        Node   kanan;  // anak kanan (idTiket lebih besar)

        Node(Laptop laptop) {
            this.data = laptop;
        }
    }

    // Akar (root) dari BST
    private Node akar;


    // -------------------------------------------------------
    //  INSERT: masukkan laptop ke dalam BST
    // -------------------------------------------------------
    public void insert(Laptop laptop) {
        akar = insertRekursif(akar, laptop);
    }

    private Node insertRekursif(Node node, Laptop laptop) {
        // Posisi kosong → buat node baru di sini
        if (node == null) {
            return new Node(laptop);
        }

        int id = laptop.getIdTiket();

        if (id < node.data.getIdTiket()) {
            node.kiri  = insertRekursif(node.kiri, laptop);   // ke kiri
        } else if (id > node.data.getIdTiket()) {
            node.kanan = insertRekursif(node.kanan, laptop);  // ke kanan
        }
        // id sama → abaikan (tidak ada duplikat)

        return node;
    }


    // -------------------------------------------------------
    //  SEARCH: cari laptop berdasarkan idTiket
    //  Mengembalikan objek Laptop jika ditemukan, null jika tidak
    // -------------------------------------------------------
    public Laptop search(int idTiket) {
        return searchRekursif(akar, idTiket);
    }

    private Laptop searchRekursif(Node node, int idTiket) {
        // Basis: node null (tidak ditemukan) ATAU ketemu
        if (node == null)                    return null;
        if (node.data.getIdTiket() == idTiket) return node.data;

        // Tentukan arah pencarian berdasarkan perbandingan id
        if (idTiket < node.data.getIdTiket()) {
            return searchRekursif(node.kiri, idTiket);   // cari ke kiri
        } else {
            return searchRekursif(node.kanan, idTiket);  // cari ke kanan
        }
    }


    // -------------------------------------------------------
    //  IN-ORDER TRAVERSAL: tampilkan semua data terurut by id
    //  Urutan: kiri → root → kanan  (menghasilkan ascending order)
    // -------------------------------------------------------
    public void tampilkanInOrder() {
        inOrderRekursif(akar);
    }

    private void inOrderRekursif(Node node) {
        if (node == null) return;
        inOrderRekursif(node.kiri);
        System.out.println("  " + node.data);
        inOrderRekursif(node.kanan);
    }
}
