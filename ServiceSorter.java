// ============================================================
//  OOP: ServiceSorter (Kelas Abstrak)
//
//  Template untuk semua strategi pengurutan laptop.
//  Setiap turunan WAJIB mengimplementasikan metode sort().
// ============================================================

public abstract class ServiceSorter {

    // Metode abstrak — implementasi ada di subclass
    public abstract void sort(Laptop[] arr);

    // -------------------------------------------------------
    //  Helper: tukar posisi dua elemen dalam array.
    //  Dipakai oleh kedua algoritma pengurutan di bawah.
    // -------------------------------------------------------
    protected void tukar(Laptop[] arr, int i, int j) {
        Laptop temp = arr[i];
        arr[i]      = arr[j];
        arr[j]      = temp;
    }

    // Helper: cetak isi array dengan label urutan
    public void tampilkanHasil(Laptop[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("  " + (i + 1) + ". " + arr[i]);
        }
    }
}


// ============================================================
//  SUBCLASS 1: UrgencySorter
//
//  Algoritma : Selection Sort
//  Kunci     : tingkatUrgensi  (ascending — 1 = paling mendesak)
//
//  Cara kerja Selection Sort:
//    - Cari nilai TERKECIL dari sisa array yang belum urut
//    - Tukar dengan elemen di posisi terdepan yang belum urut
//    - Ulangi sampai seluruh array terurut
// ============================================================

class UrgencySorter extends ServiceSorter {

    @Override
    public void sort(Laptop[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            // Asumsikan elemen terkecil ada di posisi i
            int idxMin = i;

            // Cari elemen dengan urgensi terkecil di sisa array
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getTingkatUrgensi() < arr[idxMin].getTingkatUrgensi()) {
                    idxMin = j;
                }
            }

            // Tukar hanya jika posisi minimum berubah
            if (idxMin != i) {
                tukar(arr, i, idxMin);
            }
        }
    }
}


// ============================================================
//  SUBCLASS 2: TimeSorter
//
//  Algoritma : Bubble Sort
//  Kunci     : lamaServis  (ascending — yang paling cepat duluan)
//
//  Cara kerja Bubble Sort:
//    - Bandingkan dua elemen bersebelahan
//    - Jika urutan salah, tukar keduanya
//    - "Gelembungkan" elemen terbesar ke akhir di tiap iterasi
//    - Ulangi hingga tidak ada pertukaran (array sudah urut)
// ============================================================

class TimeSorter extends ServiceSorter {

    @Override
    public void sort(Laptop[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            boolean adaPertukaran = false; // optimasi: hentikan jika sudah urut

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].getLamaServis() > arr[j + 1].getLamaServis()) {
                    tukar(arr, j, j + 1);
                    adaPertukaran = true;
                }
            }

            // Jika tidak ada pertukaran sama sekali → sudah urut, stop lebih awal
            if (!adaPertukaran) break;
        }
    }
}
