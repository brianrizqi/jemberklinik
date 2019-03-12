package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;

public class Kuota {
    @SerializedName("id_kuota")
    private int id_kuota;
    @SerializedName("kuota")
    private String kuota;
    @SerializedName("jam_awal")
    private String jam_awal;
    @SerializedName("jam_akhir")
    private String jam_akhir;

    public Kuota(int id_kuota, String kuota, String jam_awal, String jam_akhir) {
        this.id_kuota = id_kuota;
        this.kuota = kuota;
        this.jam_awal = jam_awal;
        this.jam_akhir = jam_akhir;
    }

    public int getId_kuota() {
        return id_kuota;
    }

    public void setId_kuota(int id_kuota) {
        this.id_kuota = id_kuota;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getJam_awal() {
        return jam_awal;
    }

    public void setJam_awal(String jam_awal) {
        this.jam_awal = jam_awal;
    }

    public String getJam_akhir() {
        return jam_akhir;
    }

    public void setJam_akhir(String jam_akhir) {
        this.jam_akhir = jam_akhir;
    }
}
