// ============================================================
//  MODEL: Laptop
//  Merepresentasikan setiap laptop yang masuk ke meja servis.
// ============================================================

public class Laptop {

    // --- Properti ---
    private int    idTiket;       // ID unik tiap laptop
    private int    tingkatUrgensi; // 1 (paling mendesak) s.d. 5
    private double lamaServis;    // estimasi waktu servis (jam)

    // --- Konstruktor ---
    public Laptop(int idTiket, int tingkatUrgensi, double lamaServis) {
        this.idTiket        = idTiket;
        this.tingkatUrgensi = tingkatUrgensi;
        this.lamaServis     = lamaServis;
    }

    // --- Getter ---
    public int    getIdTiket()        { return idTiket; }
    public int    getTingkatUrgensi() { return tingkatUrgensi; }
    public double getLamaServis()     { return lamaServis; }

    // --- Tampilkan info laptop dalam satu baris ---
    @Override
    public String toString() {
        return String.format(
            "Tiket #%03d | Urgensi: %d | Lama Servis: %.1f jam",
            idTiket, tingkatUrgensi, lamaServis
        );
    }
}
