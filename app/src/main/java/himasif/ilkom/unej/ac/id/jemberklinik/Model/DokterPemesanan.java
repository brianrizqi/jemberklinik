package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;


public class DokterPemesanan {
    @SerializedName("id_user")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;

    public DokterPemesanan(String id, String nama, String kategori, String jenis_kelamin) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.jenis_kelamin = jenis_kelamin;
    }


    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
