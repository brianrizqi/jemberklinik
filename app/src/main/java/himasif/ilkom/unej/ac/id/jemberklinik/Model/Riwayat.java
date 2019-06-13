package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;

public class Riwayat {
    @SerializedName("nama")
    private String nama;
    @SerializedName("keluhan")
    private String keluhan;
    @SerializedName("tanggal")
    private String tanggal;

    public Riwayat(String nama, String keluhan, String tanggal) {
        this.nama = nama;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
