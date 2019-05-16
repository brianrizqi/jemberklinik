package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;

public class Penyakit {
    @SerializedName("id_penyakit")
    private String id;
    @SerializedName("nama_penyakit")
    private String nama_penyakit;
    @SerializedName("keluhan")
    private String keluhan;

    public Penyakit(String id, String nama_penyakit, String keluhan) {
        this.id = id;
        this.nama_penyakit = nama_penyakit;
        this.keluhan = keluhan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    @Override
    public String toString() {
        return keluhan;
    }
}
