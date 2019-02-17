package himasif.ilkom.unej.ac.id.jemberklinik.Model;

import com.google.gson.annotations.SerializedName;


public class DokterPemesanan {
    @SerializedName("id_pemesanan")
    private String id_pemesanan;
    @SerializedName("id_user")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("nomor")
    private int nomor;
    @SerializedName("keluhan")
    private String keluhan;
    @SerializedName("status")
    private String status;


    public DokterPemesanan(String id_pemesanan, String id, String nama, String kategori, String jenis_kelamin, int nomor, String keluhan, String status) {
        this.id_pemesanan = id_pemesanan;
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.jenis_kelamin = jenis_kelamin;
        this.nomor = nomor;
        this.keluhan = keluhan;
        this.status = status;
    }

    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
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
